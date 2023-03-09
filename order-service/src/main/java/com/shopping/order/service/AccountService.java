package com.shopping.order.service;

import com.shopping.constant.Constant;
import com.shopping.entities.account.Account;
import com.shopping.order.bloom.RedisBloom;
import com.shopping.order.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisBloom redisBloom;

    /**
     * 项目启动时，将order服务本地的ACCOUNT表中所有所有数据加载进redis缓存
     * 每日从account服务将ACCOUNT数据同步到order服务本地库
     */
    @PostConstruct
    public void initCache(){
        log.info("开始初始化账户缓存");
        // todo 数据量较多时，不能使用以下代码，需优化为分批从数据库读取出并通过管道批量存入redis
        List<Account> accountList = accountMapper.getAccount();

        // 避免缓存同时失效，在设置缓存过期时间时加入随机数
        Random rd = new Random(5*60);
        for(Account account:accountList){
            // 将ACCOUNT数据存入redis并设置缓存时间12小时
            redisTemplate.opsForValue().set("ACCOUNT:" + account.getAcctNo(), account,60*60*12 + rd.nextInt(), TimeUnit.SECONDS);
            // 将账号添加到布隆过滤器
            redisBloom.bfadd("BF:ACCTNO",account.getAcctNo());
        }
        log.info("结束初始化账户缓存");
    }

    /**
     * 检查账户信息,检查通过返回true
     * @param acctNo
     * @return
     */
    public boolean checkAccount(String acctNo){

        // 利用布隆过滤器检查账号是否存在，不存在直接返回false
        if(!redisBloom.bfexists("BF:ACCTNO",acctNo)){
            return false;
        }

        // 根据acctNo从redis中读取缓存
        Object accountObj = redisTemplate.opsForValue().get("ACCOUNT:" + acctNo);

        Account account = null;
        if(accountObj == null){
            // 缓存中没有，从数据库中查询
            account = accountMapper.getAccountById(acctNo);
            if(account == null){
                // 数据库没有，将空对象存入redis,缓存30分钟
                account = new Account();
                redisTemplate.opsForValue().set("ACCOUNT:" + acctNo,account,30*60,TimeUnit.SECONDS);
                // 返回账户检查不通过
                return false;
            } else {
                // 将account信息存入redis缓存
                redisTemplate.opsForValue().set("ACCOUNT:" + acctNo,account,60*60*12,TimeUnit.SECONDS);
            }
        } else {
            // 缓存中有，将缓存数据转为Account对象
            account = (Account)accountObj;

            if(account.getAcctStatus() == null){
                // 缓存中是空对象，刷新到期时间30分钟
                redisTemplate.expire("ACCOUNT:" + acctNo,30*60,TimeUnit.SECONDS);
                // 返回账户检查不通过
                return false;
            } else {
                // 缓存中是真实对象，刷新到期时间12小时
                redisTemplate.expire("ACCOUNT:" + acctNo,60*60*12,TimeUnit.SECONDS);
            }
        }

        // 检查账户状态，返回检查结果
        return Constant.ACCT_STATUS_AVAILABLE.equals(account.getAcctStatus());
    }

    /**
     * 接到account服务的修改通知，进行account信息修改
     * @param account
     * @param changeType  1-新增   2-修改  3-删除
     */
    @Transactional(rollbackFor = Exception.class)
    public void changeAccount(Account account,String changeType){

        if(Constant.ACCT_CHANGE_TYPE_INSERT.equals(changeType)){
            // 将新数据插入到数据库中
            accountMapper.insert(account);
            // 将账号添加到布隆过滤器
            redisBloom.bfadd("BF:ACCTNO",account.getAcctNo());
        } else if(Constant.ACCT_CHANGE_TYPE_UPDATE.equals(changeType)){
            accountMapper.updateById(account);
        } else if(Constant.ACCT_CHANGE_TYPE_DELETE.equals(changeType)){
            accountMapper.deleteById(account.getAcctNo());
        }

        // 更新数据库完成，删除缓存数据
        redisTemplate.delete("ACCOUNT:" + account.getAcctNo());
    }

}

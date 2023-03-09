package com.shopping.account.service;

import com.shopping.constant.Constant;
import com.shopping.entities.account.Account;
import com.shopping.account.mapper.AccountMapper;
import com.shopping.account.provider.MsgProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private MsgProvider msgProvider;

    /**
     * 新增账户
     * @param account
     */
    @Transactional
    public void addAccount(Account account){
        accountMapper.insert(account);

        // 插入数据库完成后，通过rabbitmq通知order服务
        msgProvider.sendMsg(account, Constant.ACCT_CHANGE_TYPE_INSERT);
    }

}

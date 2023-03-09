package com.shopping.order.listener;

import com.alibaba.fastjson.JSONObject;
import com.shopping.order.service.AccountService;
import com.shopping.entities.account.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 接受account发送来的消息通知
 */
@Component
@Slf4j
public class MsgListener {

    @Autowired
    private AccountService accountService;

    @StreamListener(InputChannelProcessor.INPUT_CHANNEL)
    public void msgListener(String msg){
        log.info("接收到账户变更通知，通知内容：{}",msg);

        JSONObject acctJson = JSONObject.parseObject(msg);

        String changeType = acctJson.getString("changeType");
        Account account = new Account();
        account.setAcctNo(acctJson.getString("acctNo"));
        account.setUserName(acctJson.getString("userName"));
        account.setAcctStatus(acctJson.getString("acctStatus"));

        // 更新账户信息
        accountService.changeAccount(account,changeType);
    }

}

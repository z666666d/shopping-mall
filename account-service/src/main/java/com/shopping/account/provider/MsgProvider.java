package com.shopping.account.provider;

import com.alibaba.fastjson.JSONObject;
import com.shopping.entities.account.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MsgProvider {

    @Autowired
    @Qualifier(OutputChannelProcessor.OUTPUT_CHANNEL)
    private MessageChannel msgChannel;

    public void sendMsg(Account account, String changeType){
        JSONObject accountObj =  (JSONObject) JSONObject.toJSON(account);
        accountObj.put("changeType",changeType);
        String msg = accountObj.toJSONString();
        log.info("发送账户变更通知：{}" + msg);
        msgChannel.send(MessageBuilder.withPayload(msg).build(),3000);
    }
}

package com.shopping.account.provider;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputChannelProcessor {

    /**
     * 与配置文件bindings保持一致，将消息发送至指定exchange
     */
    String OUTPUT_CHANNEL = "msgChannel";

    @Output(OUTPUT_CHANNEL)
    MessageChannel msgChannel();
}

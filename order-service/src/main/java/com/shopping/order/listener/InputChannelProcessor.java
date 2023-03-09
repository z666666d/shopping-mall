package com.shopping.order.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputChannelProcessor {
    String INPUT_CHANNEL = "msgChannel";

    @Input(INPUT_CHANNEL)
    SubscribableChannel msgChannel();
}

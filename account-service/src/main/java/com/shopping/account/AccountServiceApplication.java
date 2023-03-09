package com.shopping.account;

import com.shopping.account.provider.OutputChannelProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;


@SpringBootApplication
@EnableEurekaClient
@MapperScan({"com.shopping.account.mapper.*"})
@EnableBinding(value = OutputChannelProcessor.class)
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class);
    }

}

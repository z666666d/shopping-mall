package com.shopping.order.service;

import com.shopping.entities.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单服务
 */
@Service
public class OrderService {

    @Autowired
    private AccountService accountService;

    @Transactional
    public String order(Order order){

        // 下单，检查下单账户信息
        if(accountService.checkAccount(order.getAcctNo())){
            //todo 账户检查通过，继续执行下单流程
            return "下单完成";
        } else {
            return "账户状态异常，下单失败！";
        }

    }

}

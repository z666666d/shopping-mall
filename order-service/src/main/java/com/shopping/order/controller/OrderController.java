package com.shopping.order.controller;

import com.shopping.entities.order.Order;
import com.shopping.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order")
    public String order(@RequestBody Order order){
        return orderService.order(order);
    }
}

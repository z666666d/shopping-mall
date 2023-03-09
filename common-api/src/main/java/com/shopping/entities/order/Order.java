package com.shopping.entities.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单信息
 */
@Data
public class Order {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 下单账号
     */
    private String acctNo;

    /**
     * 下单金额
     */
    private BigDecimal orderAmt;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 商品列表
     */
    private List<Item> itemList;

    /**
     * 订单备注
     */
    private String remark;
}

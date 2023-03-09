package com.shopping.entities.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {

    /**
     * 商品编号
     */
    private String itemId;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 下单数量
     */
    private int num;
}

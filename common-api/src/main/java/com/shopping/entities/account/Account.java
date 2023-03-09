package com.shopping.entities.account;

import lombok.Data;

/**
 * 账户信息
 */
@Data
public class Account {

    /**
     * 账号
     */
    private String acctNo;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 账户状态   0-不可用  1-可用
     */
    private String acctStatus;
}

package com.shopping.constant;

public class Constant {


    /**
     * 账户状态：可用
     */
    public static final String ACCT_STATUS_AVAILABLE = "1";

    /**
     * 账户状态：不可用
     */
    public static final String ACCT_STATUS_UNAVAILABLE = "0";


    /**
     * 订单状态：未支付
     */
    public static final String ORDER_STATUS_NOTPAY = "0";

    /**
     * 订单状态：支付成功
     */
    public static final String ORDER_STATUS_SUCCESS = "1";

    /**
     * 订单状态：已关闭
     */
    public static final String ORDER_STATUS_CLOSEED = "2";

    /**
     * 订单状态：已取消
     */
    public static final String ORDER_STATUS_REVOKED = "3";

    /**
     * 订单状态：已退款
     */
    public static final String ORDER_STATUS_REFUND = "4";

    /**
     * 订单状态：支付失败
     */
    public static final String ORDER_STATUS_PAYERR = "5";

    /**
     * 账户变更类型：新增
     */
    public static final String ACCT_CHANGE_TYPE_INSERT = "1";

    /**
     * 账户变更类型：修改
     */
    public static final String ACCT_CHANGE_TYPE_UPDATE = "2";

    /**
     * 账户变更类型：删除
     */
    public static final String ACCT_CHANGE_TYPE_DELETE = "3";
}

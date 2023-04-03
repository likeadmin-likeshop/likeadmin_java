package com.mdd.common.enums;

public enum RefundEnum {

    // 退款类型
    TYPE_ADMIN(1, "后台退款"),

    // 退款状态
    REFUND_ING(0, "退款中"),
    REFUND_SUCCESS(1, "退款成功"),
    REFUND_ERROR(2, "退款失败"),

    // 退款订单类型
    ORDER_TYPE_ORDER(1, "普通订单"),
    ORDER_TYPE_RECHARGE(2, "充值订单");

    /**
     * 构造方法
     */
    private final int code;
    private final String msg;
    RefundEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取状态码
     *
     * @author fzr
     * @return Long
     */
    public int getCode() {
        return this.code;
    }

    /**
     * 获取提示
     *
     * @author fzr
     * @return String
     */
    public String getMsg() {
        return this.msg;
    }

    /**
     * 订单类型标识
     *
     * @author fzr
     * @param code 编码
     * @return String
     */
    public static String getOrderType(Integer code){
        switch (code) {
            case 1:
                return "order";
            case 2:
                return "recharge";
        }
        return "未知";
    }

    /**
     * 退款类型描述
     *
     * @author fzr
     * @param code 编码
     * @return String
     */
    public static String getRefundTypeMsg(Integer code)
    {
        if (code == 1) {
            return RefundEnum.TYPE_ADMIN.getMsg();
        }
        return "未知";
    }

    /**
     * 退款状态描述
     *
     * @author fzr
     * @param code 编码
     * @return String
     */
    public static String getRefundStatusMsg(Integer code){
        switch (code) {
            case 0:
                return RefundEnum.REFUND_ING.getMsg();
            case 1:
                return RefundEnum.REFUND_SUCCESS.getMsg();
            case 2:
                return RefundEnum.REFUND_ERROR.getMsg();
        }
        return "未知";
    }

}

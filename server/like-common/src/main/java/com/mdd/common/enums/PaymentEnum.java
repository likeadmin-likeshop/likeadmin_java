package com.mdd.common.enums;

/**
 * 支付枚举
 */
public enum PaymentEnum {

    UN_PAID(0, "未支付"),
    OK_PAID(1, "已支付"),

    WALLET_PAY(1, "余额支付"),
    WX_PAY(2, "微信支付"),
    ALI_PAY(3, "支付宝支付");

    /**
     * 构造方法
     */
    private final int code;
    private final String msg;
    PaymentEnum(int code, String msg) {
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
     * 根据编码获取Msg
     *
     * @author fzr
     * @param code 类型
     * @return String
     */
    public static String getPayWayMsg(Integer code){
        switch (code) {
            case 1:
                return "余额支付";
            case 2:
                return "微信支付";
            case 3:
                return "支付宝支付";
        }
        return "未知";
    }

}

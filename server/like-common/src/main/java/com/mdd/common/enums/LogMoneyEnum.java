package com.mdd.common.enums;

public enum LogMoneyEnum {

    /**
     * 用户余额类型
     */
    UM_INC_ADMIN(1001, "平台增加余额"),
    UM_DEC_ADMIN(1002, "平台减少余额"),
    UM_INC_RECHARGE(1003, "充值余额增加"),
    UM_DEC_RECHARGE(1004, "充值余额退回");

    /**
     * 构造方法
     */
    private final int code;
    private final String msg;
    LogMoneyEnum(int code, String msg) {
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
    public static String getMsgByCode(Integer code){
        for(ClientEnum enumItem: ClientEnum.values()) {
            if (enumItem.getCode() == code) {
                return enumItem.getMsg();
            }
        }
        return null;
    }

}

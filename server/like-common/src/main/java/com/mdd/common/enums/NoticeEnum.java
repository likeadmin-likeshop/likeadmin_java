package com.mdd.common.enums;

/**
 * 通知枚举类
 */
public enum NoticeEnum {

    SMS_LOGIN_CODE(101, "登录验证码"),
    SMS_BIND_MOBILE_CODE(102, "绑定手机验证码"),
    SMS_CHANGE_MOBILE_CODE(103, "变更手机验证码"),
    SMS_FORGOT_PASSWORD_CODE(104, "找回登录密码验证码");

    /**
     * 构造方法
     */
    private final int code;
    private final String msg;
    NoticeEnum(int code, String msg) {
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

}

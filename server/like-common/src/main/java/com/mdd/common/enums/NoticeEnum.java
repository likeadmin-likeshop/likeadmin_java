package com.mdd.common.enums;

/**
 * 通知枚举类
 */
public enum NoticeEnum {

    STATUS_WAIT(0, "等待"),
    STATUS_OK(1, "成功"),
    STATUS_FAIL(2, "失败"),

    VIEW_UNREAD(0, "未读"),
    VIEW_READ(0, "已读"),

    SENDER_SYS(1, "系统类型"),
    SENDER_SMS(2, "短信类型"),
    SENDER_MNP(3, "小程序类型"),
    SENDER_OA(4, "公众号类型"),

    LOGIN_CODE(101, "登录验证码"),
    BIND_MOBILE_CODE(102, "绑定手机验证码"),
    CHANGE_MOBILE_CODE(103, "变更手机验证码"),
    FORGOT_PASSWORD_CODE(104, "找回登录密码验证码");

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

package com.mdd.common.exception;

/**
 * 登录异常类
 */
public class LoginException extends BaseException {

    public LoginException(Integer code, String msg) {
        super(code, msg);
    }

}

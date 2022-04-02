package com.hxkj.common.exception;

import com.hxkj.common.enums.HttpEnum;

/**
 * 操作系统异常
 */
public class OperateException extends BaseException {

    public OperateException(String msg) {
        super(HttpEnum.FAILED.getCode(), msg);
    }

}

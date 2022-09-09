package com.mdd.common.exception;

import com.mdd.common.enums.HttpEnum;

/**
 * 操作系统异常
 */
public class OperateException extends BaseException {

    public OperateException(String msg) {
        super(HttpEnum.FAILED.getCode(), msg);
    }

}

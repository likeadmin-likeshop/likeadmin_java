package com.mdd.common.exception;

import com.mdd.common.enums.ErrorEnum;

/**
 * 操作系统异常
 */
public class OperateException extends BaseException {

    public OperateException(String msg) {
        super(ErrorEnum.FAILED.getCode(), msg);
    }

    public OperateException(String msg, Integer errCode) {
        super(errCode, msg);
    }

}

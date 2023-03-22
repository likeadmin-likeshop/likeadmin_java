package com.mdd.common.exception;

import com.mdd.common.enums.HttpEnum;
import io.swagger.models.auth.In;

/**
 * 操作系统异常
 */
public class OperateException extends BaseException {

    public OperateException(String msg) {
        super(HttpEnum.FAILED.getCode(), msg);
    }

    public OperateException(String msg, Integer errCode) {
        super(errCode, msg);
    }

}

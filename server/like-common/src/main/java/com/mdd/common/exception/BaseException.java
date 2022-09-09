package com.mdd.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 异常基类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private Integer code;
    private String msg;

    public BaseException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

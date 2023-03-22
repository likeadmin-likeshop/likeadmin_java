package com.mdd.common.exception;

import com.mdd.common.enums.HttpEnum;

/**
 * 支付失败异常
 */
public class PaymentException extends BaseException {

    public PaymentException(String msg) {
        super(HttpEnum.PAYMENT_ERROR.getCode(), msg);
    }

    public PaymentException(String msg, Integer errCode) {
        super(errCode, msg);
    }

}

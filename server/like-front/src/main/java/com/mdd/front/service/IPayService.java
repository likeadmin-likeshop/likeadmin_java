package com.mdd.front.service;

import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.mdd.front.validate.PaymentValidate;

/**
 * 支付接口服务类
 */
public interface IPayService {

    void walletPay();

    WxPayUnifiedOrderV3Result.JsapiResult wxPay(PaymentValidate params, Integer terminal) throws Exception;

    void aliPay();

    void handlePaidNotify();

}

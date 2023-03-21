package com.mdd.front.service;

import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.mdd.front.validate.PaymentValidate;

/**
 * 支付接口服务类
 */
public interface IPayService {

    /**
     * 余额支付
     */
    void walletPay();

    /**
     * 微信支付
     *
     * @param params 参数
     * @param terminal 终端
     * @return  WxPayUnifiedOrderV3Result.JsapiResult
     * @throws Exception 异常
     */
    WxPayUnifiedOrderV3Result.JsapiResult wxPay(PaymentValidate params, Integer terminal) throws Exception;

    /**
     * 支付回调处理
     *
     * @param jsonData 回调数据
     * @param signatureHeader 请求头
     */
    void handlePaidNotify(String jsonData, SignatureHeader signatureHeader) throws WxPayException;

}

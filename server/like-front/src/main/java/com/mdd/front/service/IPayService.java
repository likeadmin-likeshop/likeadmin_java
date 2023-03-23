package com.mdd.front.service;

import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.mdd.front.validate.PaymentValidate;
import com.mdd.front.vo.PayWayListedVo;

import java.util.List;

/**
 * 支付接口服务类
 */
public interface IPayService {

    /**
     * 支付方式
     *
     * @param from 场景
     * @param terminal 总端
     * @return List<PayWayListedVo>
     */
    List<PayWayListedVo> payWay(String from, Integer terminal);

    /**
     * 发起支付
     *
     * @param params 参数
     * @param terminal 终端
     * @return Object
     */
    Object prepay(PaymentValidate params, Integer terminal);

    /**
     * 支付回调处理
     *
     * @param attach 场景码
     * @param outTradeNo 订单编号
     * @param transactionId 流水号
     */
    void handlePaidNotify(String attach, String outTradeNo, String transactionId) throws WxPayException;

}

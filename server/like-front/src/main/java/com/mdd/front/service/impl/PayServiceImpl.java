package com.mdd.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mdd.common.entity.RechargeOrder;
import com.mdd.common.mapper.RechargeOrderMapper;
import com.mdd.common.plugin.wechat.WxPayDriver;
import com.mdd.common.util.RequestUtils;
import com.mdd.front.service.IPayService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Service
public class PayServiceImpl implements IPayService {

    @Resource
    RechargeOrderMapper rechargeOrderMapper;

    @Override
    public void prepay(String scene, Integer orderId, Integer terminal) throws Exception {
        RechargeOrder rechargeOrder = rechargeOrderMapper.selectOne(
                new QueryWrapper<RechargeOrder>()
                        .eq("id", orderId)
                        .last("limit 1"));

        String orderSn = rechargeOrder.getOrderSn();
        Integer orderAmount = 1;
        String orderDesc = "余额充值";

        // 失效时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Long expireTime = System.currentTimeMillis() + 60 * 1000;
        String timeExpire = format.format(expireTime) + "+08:00";

        // 订单信息
        WxPayUnifiedOrderV3Request wxPayUnifiedOrderV3Request = new WxPayUnifiedOrderV3Request();
        wxPayUnifiedOrderV3Request.setOutTradeNo(orderSn);
        wxPayUnifiedOrderV3Request.setDescription(orderDesc);
        wxPayUnifiedOrderV3Request.setTimeExpire(timeExpire);
        wxPayUnifiedOrderV3Request.setNotifyUrl(RequestUtils.uri() + "/api/pay/notifyMnp");



        // 订单金额
        WxPayUnifiedOrderV3Request.Amount amount = new WxPayUnifiedOrderV3Request.Amount();
        amount.setTotal(orderAmount);
        amount.setCurrency("CNY");
        wxPayUnifiedOrderV3Request.setAmount(amount);

        // 付款人员
        WxPayUnifiedOrderV3Request.Payer payer = new WxPayUnifiedOrderV3Request.Payer();
        payer.setOpenid("");

        // 发起订单
        WxPayService wxPayService = WxPayDriver.handler(terminal);
        wxPayUnifiedOrderV3Request.setPayer(payer);
        WxPayUnifiedOrderV3Result.JsapiResult jsapiResult = wxPayService.createOrderV3(TradeTypeEnum.JSAPI, wxPayUnifiedOrderV3Request);
        System.out.println(jsapiResult);
    }

}

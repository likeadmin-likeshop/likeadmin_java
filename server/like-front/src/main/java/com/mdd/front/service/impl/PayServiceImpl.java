package com.mdd.front.service.impl;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mdd.common.plugin.wechat.WxPayDriver;
import com.mdd.front.service.IPayService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class PayServiceImpl implements IPayService {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public void prepay() throws Exception {

        WxPayService wxPayService = WxPayDriver.handler("1");

        String sn = "sn" + System.currentTimeMillis() / 1000;
        Long expireTime = System.currentTimeMillis() + 60 * 1000;
        String timeExpire = format.format(expireTime) + "+08:00";

        // 订单信息
        WxPayUnifiedOrderV3Request wxPayUnifiedOrderV3Request = new WxPayUnifiedOrderV3Request();
        wxPayUnifiedOrderV3Request.setOutTradeNo(sn);
        wxPayUnifiedOrderV3Request.setNotifyUrl("https://www.likeadmin.cn");
        wxPayUnifiedOrderV3Request.setDescription("充值");
        wxPayUnifiedOrderV3Request.setTimeExpire(timeExpire);

        // 订单金额
        WxPayUnifiedOrderV3Request.Amount amount = new WxPayUnifiedOrderV3Request.Amount();
        amount.setTotal(1);
        amount.setCurrency("CNY");
        wxPayUnifiedOrderV3Request.setAmount(amount);

        // 付款人
        WxPayUnifiedOrderV3Request.Payer payer = new WxPayUnifiedOrderV3Request.Payer();
        payer.setOpenid("");
        wxPayUnifiedOrderV3Request.setPayer(payer);
        WxPayUnifiedOrderV3Result.JsapiResult jsapiResult = wxPayService.createOrderV3(TradeTypeEnum.JSAPI, wxPayUnifiedOrderV3Request);
        System.out.println(jsapiResult);
    }

}

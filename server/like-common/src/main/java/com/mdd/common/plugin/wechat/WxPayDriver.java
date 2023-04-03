package com.mdd.common.plugin.wechat;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayRefundV3Result;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mdd.common.entity.setting.DevPayConfig;
import com.mdd.common.enums.ClientEnum;
import com.mdd.common.mapper.setting.DevPayConfigMapper;
import com.mdd.common.plugin.wechat.request.PaymentRequestV3;
import com.mdd.common.plugin.wechat.request.RefundRequestV3;
import com.mdd.common.util.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * 微信支付驱动
 */
@Component
public class WxPayDriver {

    private static DevPayConfigMapper devPayConfigMapper;

    private static WxPayService wxPayService;

    /**
     * 注入支付配置依赖
     */
    @Resource
    public void setDevPayConfigMapper(DevPayConfigMapper devPayConfigMapper) {
        WxPayDriver.devPayConfigMapper = devPayConfigMapper;
    }

    /**
     * 注入微信支付依赖
     */
    @Resource
    public void setWxPayService(WxPayService wxPayService) {
        WxPayDriver.wxPayService = wxPayService;
    }

    /**
     * 微信统一下单
     *
     * @param requestV3 请求参数
     * @return WxPayUnifiedOrderV3Result.JsapiResult
     * @throws Exception 异常
     */
    public static Object unifiedOrder(PaymentRequestV3 requestV3) throws Exception {
        // 订单参数
        Integer terminal       = requestV3.getTerminal();
        String openId          = requestV3.getOpenId();
        String attach          = requestV3.getAttach();
        String outTradeNo      = requestV3.getOutTradeNo();
        BigDecimal orderAmount = requestV3.getOrderAmount();
        String description     = requestV3.getDescription();

        // 失效时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Long expireTime = System.currentTimeMillis() + (30 * 60 * 1000);
        String timeExpire = format.format(expireTime) + "+08:00";

        // 订单信息
        WxPayUnifiedOrderV3Request wxPayUnifiedOrderV3Request = new WxPayUnifiedOrderV3Request();
        wxPayUnifiedOrderV3Request.setOutTradeNo(outTradeNo);
        wxPayUnifiedOrderV3Request.setDescription(description);
        wxPayUnifiedOrderV3Request.setTimeExpire(timeExpire);
        wxPayUnifiedOrderV3Request.setAttach(attach);
        wxPayUnifiedOrderV3Request.setNotifyUrl(RequestUtils.uri() + "/api/pay/notifyMnp");

        // 订单金额
        WxPayUnifiedOrderV3Request.Amount amount = new WxPayUnifiedOrderV3Request.Amount();
        amount.setTotal(AmountUtil.yuan2Fen(orderAmount.toPlainString()));
        amount.setCurrency("CNY");
        wxPayUnifiedOrderV3Request.setAmount(amount);

        // 付款人员
        WxPayUnifiedOrderV3Request.Payer payer = new WxPayUnifiedOrderV3Request.Payer();
        payer.setOpenid(openId);

        // H5平台
        TradeTypeEnum tradeTypeEnum = TradeTypeEnum.JSAPI;

        if (terminal == ClientEnum.H5.getCode()) {
            tradeTypeEnum = TradeTypeEnum.H5;
            WxPayUnifiedOrderV3Request.SceneInfo sceneInfo = new WxPayUnifiedOrderV3Request.SceneInfo();
            WxPayUnifiedOrderV3Request.H5Info h5Info = new WxPayUnifiedOrderV3Request.H5Info();
            h5Info.setType(RequestUtils.device());
            sceneInfo.setH5Info(h5Info);
            sceneInfo.setPayerClientIp(IpUtils.getHostIp());
            wxPayUnifiedOrderV3Request.setSceneInfo(sceneInfo);
        }

        // 发起订单
        WxPayService wxPayService = WxPayDriver.handler(terminal);
        wxPayUnifiedOrderV3Request.setPayer(payer);
        return wxPayService.createOrderV3(tradeTypeEnum, wxPayUnifiedOrderV3Request);
    }

    /**
     * 微信支付-申请退款
     * 文档地址: https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
     * 接口链接: https://api.mch.weixin.qq.com/secapi/pay/refund
     *
     * @param request 请求参数
     * @return WxPayRefundV3Result 退款结果
     * @throws WxPayException 退款异常
     */
    public static WxPayRefundV3Result refund(RefundRequestV3 request) throws WxPayException {
        WxPayRefundV3Request requestObj = new WxPayRefundV3Request();
        requestObj.setTransactionId(request.getTransactionId());
        requestObj.setOutTradeNo(request.getOutTradeNo());
        requestObj.setOutRefundNo(request.getOutRefundNo());
        requestObj.setReason(request.getReason());
        requestObj.setNotifyUrl(request.getNotifyUrl());
        requestObj.setSubMchid(request.getSubMchid());
        requestObj.setGoodsDetails(request.getGoodsDetails());

        WxPayRefundV3Request.Amount amount = new WxPayRefundV3Request.Amount();
        amount.setRefund(request.getRefundAmount());
        amount.setTotal(request.getTotalAmount());
        amount.setCurrency(StringUtils.isEmpty(request.getCurrency()) ? "CNY" : request.getCurrency());
        requestObj.setAmount(amount);

        return wxPayService.refundV3(requestObj);
    }

    /**
     * 对象句柄
     *
     * @author fzr
     * @return WxPayService
     */
    public static WxPayService handler(Integer terminal) {
        if (ClientEnum.OA.getCode() == terminal) {
            resetConfig("oa");
        } else {
            resetConfig("mnp");
        }
        return wxPayService;
    }

    /**
     * 重设配置
     *
     * @author fzr
     * @param type 类型
     */
    private static void resetConfig(String type) {
        DevPayConfig config = devPayConfigMapper.selectOne(
                new QueryWrapper<DevPayConfig>()
                        .eq("way", 2)
                        .last("limit 1"));

        String scene = type.equals("oa") ? "oa_channel" : "mp_channel";
        String appId = ConfigUtils.get(scene, "appId", "");

        Map<String, String> params = MapUtils.jsonToMap(config.getParams().toString());
        String mchId = params.get("mch_id");
        String paySignKey  = params.get("pay_sign_key");
        byte[] privateCert = params.getOrDefault("private_cert", "").getBytes();
        byte[] privateKey  = params.getOrDefault("private_key", "").getBytes();

        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(appId);
        payConfig.setMchId(mchId);
        payConfig.setApiV3Key(paySignKey);
        payConfig.setPrivateKeyContent(privateKey);
        payConfig.setPrivateCertContent(privateCert);
        payConfig.setUseSandboxEnv(false);
        wxPayService.setConfig(payConfig);
    }

}

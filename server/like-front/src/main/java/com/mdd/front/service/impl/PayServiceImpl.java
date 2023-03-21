package com.mdd.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyV3Result;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mdd.common.entity.user.UserAuth;
import com.mdd.common.enums.ClientEnum;
import com.mdd.common.mapper.user.UserAuthMapper;
import com.mdd.common.plugin.wechat.WxPayDriver;
import com.mdd.common.util.AmountUtil;
import com.mdd.common.util.IpUtils;
import com.mdd.common.util.RequestUtils;
import com.mdd.common.util.StringUtils;
import com.mdd.front.service.IPayService;
import com.mdd.front.validate.PaymentValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Slf4j
@Service
public class PayServiceImpl implements IPayService {

    @Resource
    UserAuthMapper userAuthMapper;

    /**
     * 零钱支付
     */
    @Override
    public void walletPay() {

    }

    /**
     * 微信支付
     *
     * @param params 支付参数
     * @param terminal 终端
     * @throws Exception 异常
     */
    @Override
    public WxPayUnifiedOrderV3Result.JsapiResult wxPay(PaymentValidate params, Integer terminal) throws Exception {
        // 订单参数
        Integer userId  = params.getUserId();
        String orderSn  = params.getOrderSn();
        BigDecimal orderAmount = params.getOrderAmount();
        String description = params.getDescription();

        // 查询OpenId
        String openId = "";
        UserAuth userAuth = userAuthMapper.selectOne(new QueryWrapper<UserAuth>()
                .eq("user_id", userId)
                .eq("terminal", terminal)
                .last("limit 1"));

        if (StringUtils.isNotNull(userAuth)) {
            openId = userAuth.getOpenid();
        }

        // 失效时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Long expireTime = System.currentTimeMillis() + 60 * 1000;
        String timeExpire = format.format(expireTime) + "+08:00";

        // 订单信息
        WxPayUnifiedOrderV3Request wxPayUnifiedOrderV3Request = new WxPayUnifiedOrderV3Request();
        wxPayUnifiedOrderV3Request.setOutTradeNo(orderSn);
        wxPayUnifiedOrderV3Request.setDescription(description);
        wxPayUnifiedOrderV3Request.setTimeExpire(timeExpire);
        wxPayUnifiedOrderV3Request.setNotifyUrl("https://likeadmin-java.yixiangonline.com/api/pay/notifyMnp");

        // 订单金额
        WxPayUnifiedOrderV3Request.Amount amount = new WxPayUnifiedOrderV3Request.Amount();
        amount.setTotal(AmountUtil.yuan2Fen(orderAmount.toPlainString()));
        amount.setCurrency("CNY");
        wxPayUnifiedOrderV3Request.setAmount(amount);

        // 付款人员
        WxPayUnifiedOrderV3Request.Payer payer = new WxPayUnifiedOrderV3Request.Payer();
        payer.setOpenid(openId);

        // H5平台
        if (terminal == ClientEnum.H5.getCode()) {
            WxPayUnifiedOrderV3Request.SceneInfo sceneInfo = new WxPayUnifiedOrderV3Request.SceneInfo();
            WxPayUnifiedOrderV3Request.H5Info h5Info = new WxPayUnifiedOrderV3Request.H5Info();
            h5Info.setType("android");
            sceneInfo.setH5Info(h5Info);
            sceneInfo.setPayerClientIp(IpUtils.getHostIp());
            sceneInfo.setDeviceId("1");
            wxPayUnifiedOrderV3Request.setSceneInfo(sceneInfo);
        }

        // 发起订单
        WxPayService wxPayService = WxPayDriver.handler(terminal);
        wxPayUnifiedOrderV3Request.setPayer(payer);
        return wxPayService.createOrderV3(TradeTypeEnum.JSAPI, wxPayUnifiedOrderV3Request);
    }

    /**
     * 支付回调处理
     */
    public void handlePaidNotify(String jsonData, SignatureHeader signatureHeader) throws WxPayException {
        log.info("微信传来的json-------");
        log.info(jsonData);
        log.info("signatureHeader------------");
        log.info(signatureHeader.toString());

        WxPayService wxPayService = WxPayDriver.handler(ClientEnum.MNP.getCode());
        WxPayOrderNotifyV3Result.DecryptNotifyResult notifyResult = wxPayService.parseOrderNotifyV3Result(jsonData, signatureHeader).getResult();

        String transactionId = notifyResult.getTransactionId();
        String outTradeNo = notifyResult.getOutTradeNo();

        log.info("transactionId-------");
        log.info(transactionId);
        log.info("outTradeNo-------");
        log.info(outTradeNo);
    }

}

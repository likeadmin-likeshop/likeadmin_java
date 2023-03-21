package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.entity.RechargeOrder;
import com.mdd.common.enums.PaymentEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.RechargeOrderMapper;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IPayService;
import com.mdd.front.validate.PaymentValidate;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/pay")
@Api(tags = "支付管理")
public class PayController {

    @Resource
    RechargeOrderMapper rechargeOrderMapper;

    @Resource
    IPayService iPayService;

    @GetMapping("/payWay")
    public AjaxResult<Object> payWay() {
        return AjaxResult.success();
    }

    /**
     * 预支付
     *
     * @return AjaxResult<Object>
     */
    @PostMapping("/prepay")
    public AjaxResult<Object> prepay(@Validated @RequestBody PaymentValidate paymentValidate) {
        String scene = paymentValidate.getScene();
        Integer payWay   = paymentValidate.getPayWay();
        Integer orderId  = paymentValidate.getOrderId();
        Integer terminal = LikeFrontThreadLocal.getTerminal();

        // 订单处理
        int payStatus = 0;
        switch (scene) {
            case "recharge":
                RechargeOrder rechargeOrder = rechargeOrderMapper.selectOne(
                        new QueryWrapper<RechargeOrder>()
                                .eq("id", orderId)
                                .last("limit 1"));

                Assert.notNull(rechargeOrder, "订单不存在");

                paymentValidate.setOrderSn(rechargeOrder.getOrderSn());
                paymentValidate.setUserId(rechargeOrder.getUserId());
                paymentValidate.setOrderAmount(rechargeOrder.getOrderAmount());
                paymentValidate.setDescription("余额充值");
                payStatus = rechargeOrder.getPayStatus();
                break;
            case "order":
                // todo 其它订单处理
                break;
        }

        // 订单校验
        if (payStatus != 0) {
            throw new OperateException("订单已支付");
        }

        // 发起支付
        try {
            switch (payWay) {
                case 1: // 余额支付
                    iPayService.walletPay();
                    break;
                case 2: // 微信支付
                    WxPayUnifiedOrderV3Result.JsapiResult result = iPayService.wxPay(paymentValidate, terminal);
                    return AjaxResult.success(result);
            }
        } catch (Exception e) {
            throw new OperateException(e.getMessage());
        }

        return AjaxResult.success();
    }

    /**
     * 微信支付回调
     *
     * @return AjaxResult<Object>
     */
    @NotLogin
    @PostMapping("/notifyMnp")
    public AjaxResult<Object> notifyMnp(@RequestBody String jsonData, HttpServletRequest request) throws WxPayException {
        SignatureHeader signatureHeader = this.getWxRequestHeader(request);
        iPayService.handlePaidNotify(jsonData, signatureHeader);
        return AjaxResult.success();
    }

    /**
     * 微信支付回调签名相关
     *
     * @param request HttpServletRequest
     * @return SignatureHeader
     */
    private SignatureHeader getWxRequestHeader(HttpServletRequest request) {
        String signature = request.getHeader("wechatpay-signature");
        String nonce     = request.getHeader("wechatpay-nonce");
        String serial    = request.getHeader("wechatpay-serial");
        String timestamp = request.getHeader("wechatpay-timestamp");

        SignatureHeader signatureHeader = new SignatureHeader();
        signatureHeader.setSignature(signature);
        signatureHeader.setNonce(nonce);
        signatureHeader.setSerial(serial);
        signatureHeader.setTimeStamp(timestamp);
        return signatureHeader;
    }

}

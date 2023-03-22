package com.mdd.front.controller;

import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyV3Result;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.entity.RechargeOrder;
import com.mdd.common.enums.ClientEnum;
import com.mdd.common.enums.PaymentEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.exception.PaymentException;
import com.mdd.common.mapper.RechargeOrderMapper;
import com.mdd.common.plugin.wechat.WxPayDriver;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IPayService;
import com.mdd.front.validate.PaymentValidate;
import com.mdd.front.vo.PayWayListedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/pay")
@Api(tags = "支付管理")
public class PayController {

    @Resource
    RechargeOrderMapper rechargeOrderMapper;

    @Resource
    IPayService iPayService;

    @GetMapping("/payWay")
    @ApiOperation("支付方式")
    public AjaxResult<List<PayWayListedVo>> payWay(@Validated @NotNull(message = "from参数丢失") @RequestParam String from) {
        Integer terminal = LikeFrontThreadLocal.getTerminal();

        List<PayWayListedVo> list = iPayService.payWay(from, terminal);
        return AjaxResult.success(list);
    }

    @PostMapping("/prepay")
    @ApiOperation("发起支付")
    public AjaxResult<Object> prepay(@Validated @RequestBody PaymentValidate paymentValidate) {
        // 接收参数
        String scene = paymentValidate.getScene();
        Integer payWay   = paymentValidate.getPayWay();
        Integer orderId  = paymentValidate.getOrderId();
        Integer terminal = LikeFrontThreadLocal.getTerminal();

        // 订单处理
        int payStatus = 0;
        switch (scene) {
            case "recharge":
                RechargeOrder rechargeOrder = rechargeOrderMapper.selectById(orderId);

                Assert.notNull(rechargeOrder, "订单不存在");
                Assert.isTrue(!payWay.equals(PaymentEnum.WALLET_PAY.getCode()), "支付类型不被支持");

                paymentValidate.setAttach("recharge");
                paymentValidate.setOrderSn(rechargeOrder.getOrderSn());
                paymentValidate.setUserId(rechargeOrder.getUserId());
                paymentValidate.setOrderAmount(rechargeOrder.getOrderAmount());
                paymentValidate.setDescription("余额充值");
                payStatus = rechargeOrder.getPayStatus();

                rechargeOrder.setPayWay(payWay);
                rechargeOrderMapper.updateById(rechargeOrder);
                break;
            case "order":
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
                    String attach = paymentValidate.getAttach();
                    String orderSn = paymentValidate.getOrderSn();
                    iPayService.handlePaidNotify(attach, orderSn, null);
                    break;
                case 2: // 微信支付
                    WxPayUnifiedOrderV3Result.JsapiResult result = iPayService.wxPay(paymentValidate, terminal);
                    return AjaxResult.success(result);
            }
        } catch (Exception e) {
            throw new PaymentException(e.getMessage());
        }

        throw new PaymentException("发起支付失败");
    }

    @NotLogin
    @PostMapping("/notifyMnp")
    @ApiOperation("微信支付回调")
    public AjaxResult<Object> notifyMnp(@RequestBody String jsonData, HttpServletRequest request) throws WxPayException {
        SignatureHeader signatureHeader = this.getWxRequestHeader(request);
        WxPayService wxPayService = WxPayDriver.handler(ClientEnum.MNP.getCode());
        WxPayOrderNotifyV3Result.DecryptNotifyResult notifyResult = wxPayService.parseOrderNotifyV3Result(jsonData, signatureHeader).getResult();

        String transactionId = notifyResult.getTransactionId();
        String outTradeNo = notifyResult.getOutTradeNo();
        String attach =  notifyResult.getAttach();

        iPayService.handlePaidNotify(attach, outTradeNo, transactionId);
        return AjaxResult.success();
    }

    @ApiOperation("微信支付回调签名相关")
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

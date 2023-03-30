package com.mdd.front.controller;

import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.entity.RechargeOrder;
import com.mdd.common.enums.ClientEnum;
import com.mdd.common.enums.PaymentEnum;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.RechargeOrderMapper;
import com.mdd.common.plugin.wechat.WxPayDriver;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IPayService;
import com.mdd.front.validate.PaymentValidate;
import com.mdd.front.vo.pay.PayWayListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

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
    public AjaxResult<PayWayListVo> payWay(@Validated @NotNull(message = "from参数丢失") @RequestParam String from,
                                                 @Validated @NotNull(message = "orderId参数丢失") @RequestParam Integer orderId) {
        Integer terminal = LikeFrontThreadLocal.getTerminal();

        PayWayListVo list = iPayService.payWay(from, orderId, terminal);
        return AjaxResult.success(list);
    }

    @PostMapping("/prepay")
    @ApiOperation("发起支付")
    public AjaxResult<Object> prepay(@Validated @RequestBody PaymentValidate requestObj) {
        // 接收参数
        String scene     = requestObj.getScene();
        Integer payWay   = requestObj.getPayWay();
        Integer orderId  = requestObj.getOrderId();
        Integer terminal = LikeFrontThreadLocal.getTerminal();

        // 订单处理
        int payStatus = 0;
        switch (scene) {
            case "recharge":
                RechargeOrder rechargeOrder = rechargeOrderMapper.selectById(orderId);

                Assert.notNull(rechargeOrder, "订单不存在");
                Assert.isTrue(!payWay.equals(PaymentEnum.WALLET_PAY.getCode()), "支付类型不被支持");

                requestObj.setUserId(rechargeOrder.getUserId());
                requestObj.setOutTradeNo(rechargeOrder.getOrderSn());
                requestObj.setOrderAmount(rechargeOrder.getOrderAmount());
                requestObj.setDescription("余额充值");
                requestObj.setAttach("recharge");
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
        Object result = iPayService.prepay(requestObj, terminal);
        return AjaxResult.success(result);
    }

    @NotLogin
    @PostMapping("/notifyMnp")
    @ApiOperation("微信支付回调")
    public AjaxResult<Object> notifyMnp(@RequestBody String jsonData, HttpServletRequest request) throws WxPayException {
        // 构建签名
        SignatureHeader signatureHeader = new SignatureHeader();
        signatureHeader.setSignature(request.getHeader("wechatpay-signature"));
        signatureHeader.setNonce(request.getHeader("wechatpay-nonce"));
        signatureHeader.setSerial(request.getHeader("wechatpay-serial"));
        signatureHeader.setTimeStamp(request.getHeader("wechatpay-timestamp"));

        // 解密数据
        WxPayService wxPayService = WxPayDriver.handler(ClientEnum.MNP.getCode());
        WxPayOrderNotifyV3Result.DecryptNotifyResult notifyResult = wxPayService.parseOrderNotifyV3Result(jsonData, signatureHeader).getResult();

        // 取出数据
        String transactionId = notifyResult.getTransactionId();
        String outTradeNo = notifyResult.getOutTradeNo();
        String attach =  notifyResult.getAttach();

        // 处理回调
        iPayService.handlePaidNotify(attach, outTradeNo, transactionId);
        return AjaxResult.success();
    }

}

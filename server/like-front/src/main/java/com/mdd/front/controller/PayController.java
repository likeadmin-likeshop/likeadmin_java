package com.mdd.front.controller;

import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IPayService;
import com.mdd.front.validate.PayPrepayValidate;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/pay")
@Api(tags = "支付管理")
public class PayController {

    @Resource
    IPayService iPayService;

    /**
     * 预支付
     *
     * @return AjaxResult<Object>
     */
    @NotLogin
    @PostMapping("/prepay")
    public AjaxResult<Object> prepay(@Validated @RequestBody PayPrepayValidate payPrepayValidate) throws Exception {
        String scene = payPrepayValidate.getScene();
        Integer orderId = payPrepayValidate.getOrderId();
        Integer terminal = LikeFrontThreadLocal.getTerminal();

        iPayService.prepay(scene, orderId, terminal);
        return AjaxResult.success();
    }

    /**
     * 微信支付回调
     *
     * @return AjaxResult<Object>
     */
    @NotLogin
    @PostMapping("/notifyMnp")
    public AjaxResult<Object> notifyMnp() {
        return AjaxResult.success();
    }

}

package com.mdd.front.controller;

import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.front.service.IPayService;
import io.swagger.annotations.Api;
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
    public AjaxResult<Object> prepay() throws Exception {
        iPayService.prepay();
        return AjaxResult.success();
    }

    /**
     * 微信支付回调
     *
     * @return AjaxResult<Object>
     */
    public AjaxResult<Object> notifyMnp() {
        return AjaxResult.success();
    }

}

package com.mdd.front.controller;

import com.mdd.common.core.AjaxResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recharge")
@Api(tags = "充值管理")
public class RechargeController {

    @GetMapping("/list")
    public AjaxResult<Object> list() {
        return AjaxResult.success();
    }

    @PostMapping("/placeOrder")
    public AjaxResult<Object> placeOrder() {
        return AjaxResult.success();
    }

}

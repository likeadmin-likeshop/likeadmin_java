package com.mdd.admin.controller.setting;


import com.mdd.admin.service.ISettingPaymentService;
import com.mdd.admin.validate.setting.SettingPaymentValidate;
import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.entity.setting.DevPayConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/setting/payment")
@Api(tags = "配置支付参数")
public class SettingPaymentController {

    @Resource
    ISettingPaymentService iSettingPaymentService;

    public AjaxResult<Object> method() {
        return AjaxResult.success();
    }

    @NotLogin
    @GetMapping("/list")
    @ApiOperation(value="支付渠道列表")
    public AjaxResult<Object> list() {
        List<DevPayConfig> list = iSettingPaymentService.list();
        return AjaxResult.success(list);
    }

    @NotLogin
    @PostMapping("/edit")
    @ApiOperation(value="支付渠道编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody SettingPaymentValidate paymentValidate) {
        iSettingPaymentService.edit(paymentValidate);
        return AjaxResult.success();
    }

}

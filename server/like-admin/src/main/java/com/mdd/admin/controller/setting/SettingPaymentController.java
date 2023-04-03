package com.mdd.admin.controller.setting;


import com.mdd.admin.service.ISettingPaymentService;
import com.mdd.admin.validate.setting.SettingPayConfigValidate;
import com.mdd.admin.validate.setting.SettingPayMethodValidate;
import com.mdd.admin.vo.setting.SettingPaymentMethodVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.entity.setting.DevPayConfig;
import com.mdd.common.validator.annotation.IDMust;
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

    @GetMapping("/method")
    @ApiOperation(value="支付方式列表")
    public AjaxResult<List<List<SettingPaymentMethodVo>>> method() {
        List<List<SettingPaymentMethodVo>> list = iSettingPaymentService.method();
        return AjaxResult.success(list);
    }

    @GetMapping("/list")
    @ApiOperation(value="支付配置列表")
    public AjaxResult<List<DevPayConfig>> list() {
        List<DevPayConfig> list = iSettingPaymentService.list();
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @ApiOperation(value="支付配置详情")
    public AjaxResult<Object> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        DevPayConfig vo = iSettingPaymentService.detail(id);
        return AjaxResult.success(vo);
    }

    @PostMapping("/editConfig")
    @ApiOperation(value="支付配置编辑")
    public AjaxResult<Object> editConfig(@Validated @RequestBody SettingPayConfigValidate configValidate) {
        iSettingPaymentService.editConfig(configValidate);
        return AjaxResult.success();
    }

    @PostMapping("/editMethod")
    @ApiOperation(value="支付方式编辑")
    public AjaxResult<Object> editMethod(@Validated @RequestBody SettingPayMethodValidate methodValidate) {
        iSettingPaymentService.editMethod(methodValidate);
        return AjaxResult.success();
    }

}

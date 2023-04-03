package com.mdd.admin.controller.marketing;

import com.mdd.admin.service.IMarketingRechargeService;
import com.mdd.admin.validate.marketing.MarketingRechargeValidate;
import com.mdd.admin.vo.marketing.MarketingRechargeVo;
import com.mdd.common.core.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/marketing/recharge")
@Api("营销充值管理")
public class MarketingRechargeController {

    @Resource
    IMarketingRechargeService iMarketingRechargeService;

    @GetMapping("/detail")
    @ApiModelProperty(value = "充值配置详情")
    public AjaxResult<MarketingRechargeVo> detail() {
        MarketingRechargeVo vo = iMarketingRechargeService.detail();
        return AjaxResult.success(vo);
    }

    @PostMapping("/save")
    @ApiModelProperty(value = "充值配置保存")
    public AjaxResult<Object> save(@Validated @RequestBody MarketingRechargeValidate rechargeValidate) {
        iMarketingRechargeService.save(rechargeValidate);
        return AjaxResult.success();
    }

}

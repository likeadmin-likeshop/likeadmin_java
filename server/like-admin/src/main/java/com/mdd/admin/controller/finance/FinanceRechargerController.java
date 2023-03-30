package com.mdd.admin.controller.finance;

import com.mdd.admin.service.IFinanceRechargerService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.finance.FinanceRechargeSearchValidate;
import com.mdd.admin.vo.finance.FinanceRechargeListVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/finance/recharger")
@Api("充值记录管理")
public class FinanceRechargerController {

    @Resource
    IFinanceRechargerService iFinanceRechargerService;

    @GetMapping("/list")
    @ApiOperation("记录列表")
    public AjaxResult<Object> list(@Validated PageValidate pageValidate,
                                   @Validated FinanceRechargeSearchValidate searchValidate) {
        PageResult<FinanceRechargeListVo> list = iFinanceRechargerService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

}

package com.mdd.admin.controller.finance;

import com.mdd.admin.service.IFinanceRefundService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.finance.FinanceRefundSearchValidate;
import com.mdd.admin.vo.finance.FinanceRefundListVo;
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
@RequestMapping("api/finance/refund")
@Api("退款记录管理")
public class FinanceRefundController {

    @Resource
    IFinanceRefundService iFinanceRefundService;

    @GetMapping("/list")
    @ApiOperation("记录列表")
    public AjaxResult<Object> list(@Validated PageValidate pageValidate,
                                   @Validated FinanceRefundSearchValidate searchValidate) {
        PageResult<FinanceRefundListVo> list = iFinanceRefundService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

}

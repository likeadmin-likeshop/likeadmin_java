package com.mdd.admin.controller.finance;

import com.mdd.admin.service.IFinanceRefundService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.finance.FinanceRefundSearchValidate;
import com.mdd.admin.vo.finance.FinanceRefundListVo;
import com.mdd.admin.vo.finance.FinanceRefundLogVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/finance/refund")
@Api("退款记录管理")
public class FinanceRefundController {

    @Resource
    IFinanceRefundService iFinanceRefundService;

    @GetMapping("/stat")
    @ApiOperation("退还统计")
    public AjaxResult<Object> stat() {
        return AjaxResult.success();
    }

    @GetMapping("/list")
    @ApiOperation("退款记录")
    public AjaxResult<Object> list(@Validated PageValidate pageValidate,
                                   @Validated FinanceRefundSearchValidate searchValidate) {
        PageResult<FinanceRefundListVo> list = iFinanceRefundService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    @GetMapping("/log")
    @ApiOperation("退款日志")
    public AjaxResult<Object> log(@Validated @IDMust() @RequestParam("id") Integer id) {
        List<FinanceRefundLogVo> list = iFinanceRefundService.log(id);
        return AjaxResult.success(list);
    }

}

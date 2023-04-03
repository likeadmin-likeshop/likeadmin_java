package com.mdd.admin.controller.finance;

import com.mdd.admin.service.IFinanceWalletService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.finance.FinanceWalletSearchValidate;
import com.mdd.admin.vo.finance.FinanceWalletListVo;
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
@RequestMapping("api/finance/wallet")
@Api("余额明细管理")
public class FinanceWalletController {

    @Resource
    IFinanceWalletService iFinanceWalletService;

    @GetMapping("/list")
    @ApiOperation("记录列表")
    public AjaxResult<Object> list(@Validated PageValidate pageValidate,
                                   @Validated FinanceWalletSearchValidate searchValidate) {
        PageResult<FinanceWalletListVo> list = iFinanceWalletService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

}

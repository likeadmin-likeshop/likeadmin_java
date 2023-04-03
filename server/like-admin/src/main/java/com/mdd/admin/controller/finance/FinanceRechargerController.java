package com.mdd.admin.controller.finance;

import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.admin.service.IFinanceRechargerService;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.finance.FinanceRechargeSearchValidate;
import com.mdd.admin.vo.finance.FinanceRechargeListVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/finance/recharger")
@Api("充值记录管理")
public class FinanceRechargerController {

    @Resource
    IFinanceRechargerService iFinanceRechargerService;

    @GetMapping("/list")
    @ApiOperation("充值记录")
    public AjaxResult<PageResult<FinanceRechargeListVo>> list(@Validated PageValidate pageValidate,
                                   @Validated FinanceRechargeSearchValidate searchValidate) {
        PageResult<FinanceRechargeListVo> list = iFinanceRechargerService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    @PostMapping("/refund")
    @ApiOperation("发起退款")
    public AjaxResult<Object> refund(@Validated @RequestBody IdValidate idValidate) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();

        iFinanceRechargerService.refund(idValidate.getId(), adminId);
        return AjaxResult.success();
    }

    @PostMapping("/refundAgain")
    @ApiModelProperty("重新退款")
    public AjaxResult<Object> refundAgain(@Validated @RequestBody IdValidate idValidate) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();

        iFinanceRechargerService.refundAgain(idValidate.getId(), adminId);
        return AjaxResult.success();
    }

}

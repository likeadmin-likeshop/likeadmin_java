package com.mdd.front.controller;

import com.mdd.common.core.AjaxResult;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IRechargeService;
import com.mdd.front.validate.RechargeValidate;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/recharge")
@Api(tags = "充值管理")
public class RechargeController {

    @Resource
    IRechargeService iRechargeService;

    @GetMapping("/list")
    public AjaxResult<Object> list() {
        return AjaxResult.success();
    }

    /**
     * 创建订单
     *
     * @param rechargeValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/placeOrder")
    public AjaxResult<Object> placeOrder(@Validated @RequestBody RechargeValidate rechargeValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();
        Integer terminal = LikeFrontThreadLocal.getTerminal();

        Map<String, Object> result = iRechargeService.placeOrder(userId, terminal, rechargeValidate);
        return AjaxResult.success(result);
    }

}

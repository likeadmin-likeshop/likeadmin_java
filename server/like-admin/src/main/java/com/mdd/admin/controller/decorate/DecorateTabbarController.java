package com.mdd.admin.controller.decorate;

import com.mdd.admin.aop.Log;
import com.mdd.admin.service.IDecorateTabbarService;
import com.mdd.admin.validate.decorate.DecorateTabsValidate;
import com.mdd.admin.vo.decorate.DecorateTabbarVo;
import com.mdd.common.core.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/decorate/tabbar")
@Api(tags = "装修导航管理")
public class DecorateTabbarController {

    @Resource
    IDecorateTabbarService iDecorateTabbarService;

    @GetMapping("/detail")
    @ApiOperation(value="底部导航详情")
    public AjaxResult<DecorateTabbarVo> detail() {
        DecorateTabbarVo vo = iDecorateTabbarService.detail();
        return AjaxResult.success(vo);
    }

    @Log(title = "底部导航编辑")
    @PostMapping("/save")
    @ApiOperation(value="底部导航编辑")
    public AjaxResult<Object> save(@Validated @RequestBody DecorateTabsValidate tabsValidate) {
        iDecorateTabbarService.save(tabsValidate);
        return AjaxResult.success();
    }

}

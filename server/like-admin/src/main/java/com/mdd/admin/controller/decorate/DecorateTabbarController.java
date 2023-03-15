package com.mdd.admin.controller.decorate;

import com.mdd.admin.service.IDecorateTabbarService;
import com.mdd.admin.validate.DecorateTabsValidate;
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

    /**
     * 底部导航详情
     *
     * @author fzr
     * @return AjaxResult<DecorateTabbarVo>
     */
    @GetMapping("/detail")
    @ApiOperation(value="底部导航详情")
    public AjaxResult<DecorateTabbarVo> detail() {
        DecorateTabbarVo vo = iDecorateTabbarService.detail();
        return AjaxResult.success(vo);
    }

    /**
     * 底部导航保存
     *
     * @author fzr
     * @param tabsValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/save")
    @ApiOperation(value="底部导航保存")
    public AjaxResult<Object> save(@Validated @RequestBody DecorateTabsValidate tabsValidate) {
        iDecorateTabbarService.save(tabsValidate);
        return AjaxResult.success();
    }

}

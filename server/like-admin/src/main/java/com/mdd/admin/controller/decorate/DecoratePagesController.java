package com.mdd.admin.controller.decorate;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.IDecoratePageService;
import com.mdd.admin.validate.DecoratePageValidate;
import com.mdd.admin.vo.decorate.DecoratePageVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 页面装修管理
 */
@RestController
@RequestMapping("api/decorate/pages")
public class DecoratePagesController {

    @Resource
    IDecoratePageService iDecoratePageService;

    /**
     * 页面装修详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<DecoratePageVo>
     */
    @GetMapping("/detail")
    public AjaxResult<DecoratePageVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        DecoratePageVo vo = iDecoratePageService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 页面装修保存
     *
     * @author fzr
     * @param decoratePageValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "页面装修保存")
    @PostMapping("/save")
    public AjaxResult<Object> save(@RequestBody DecoratePageValidate decoratePageValidate) {
        iDecoratePageService.save(decoratePageValidate);
        return AjaxResult.success();
    }

}

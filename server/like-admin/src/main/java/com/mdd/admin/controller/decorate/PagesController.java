package com.mdd.admin.controller.decorate;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.decorate.IDecoratePageService;
import com.mdd.admin.validate.decorate.DecoratePageParam;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 页面装修管理
 */
@RestController("decoratePagesController")
@RequestMapping("api/decorate/pages")
public class PagesController {

    @Resource
    IDecoratePageService iDecoratePageService;

    /**
     * 页面装修详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        Map<String, Object> map = iDecoratePageService.detail(id);
        return AjaxResult.success(map);
    }

    /**
     * 页面装修保存
     *
     * @author fzr
     * @param decoratePageParam 参数
     * @return AjaxResult
     */
    @Log(title = "页面装修保存")
    @PostMapping("/save")
    public AjaxResult save(@RequestBody DecoratePageParam decoratePageParam) {
        iDecoratePageService.save(decoratePageParam);
        return AjaxResult.success();
    }

}

package com.hxkj.generator.controller;

import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.validator.annotation.IDMust;
import com.hxkj.generator.service.IGenerateService;
import com.hxkj.generator.validate.PageParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/gen")
public class GenController {

    @Resource
    IGenerateService iGenerateService;

    /**
     * 库列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/db")
    public Object db(@Validated PageParam pageParam,
                     @RequestParam Map<String, String> params) {
        PageResult<Map<String, String>> list = iGenerateService.db(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 生成列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/genList")
    public Object genList(@Validated PageParam pageParam,
                          @RequestParam Map<String, String> params) {
        PageResult<Map<String, Object>> list = iGenerateService.genList(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 生成详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/genDetail")
    public Object genDetail(@Validated @IDMust() @RequestParam("id") Integer id) {
        Map<String, Object> maps = iGenerateService.genDetail(id);
        return AjaxResult.success(maps);
    }

    /**
     * 导入表结构
     *
     * @param tables 参数
     * @return Object
     */
    @PostMapping("/importTable")
    public Object importTable(String tables) {
        String[] tableNames = tables.split(",");
        iGenerateService.importTable(tableNames);
        return AjaxResult.success();
    }

    /**
     * 编辑表结构
     *
     * @author fzr
     * @return Object
     */
    @PostMapping("/editTable")
    public Object editTable(@Validated @IDMust() @RequestParam("id") Integer id) {
        iGenerateService.editTable(id);
        return AjaxResult.success();
    }

    /**
     * 删除表结构
     *
     * @author fzr
     * @return Object
     */
    @PostMapping("/deleteTable")
    public Object deleteTable(@Validated @IDMust() @RequestParam("id") Integer id) {
        iGenerateService.deleteTable(id);
        return AjaxResult.success();
    }

    /**
     * 同步表结构
     *
     * @author fzr
     * @return Object
     */
    public Object syncTable(@Validated @IDMust() @RequestParam("id") Integer id) {
        return null;
    }

    /**
     * 预览代码
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/previewCode")
    public Object previewCode(@Validated @IDMust() @RequestParam("id") Integer id) {
        Map<String, String> map = iGenerateService.previewCode(id);
        return AjaxResult.success(map);
    }

    /**
     * 生成代码
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("genCode")
    public Object genCode(@Validated @IDMust() @RequestParam("id") Integer id) {
        return null;
    }

}

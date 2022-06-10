package com.hxkj.generator.controller;

import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
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
     * 数据表列表
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
     * 导入数据表
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
     * 预览代码
     *
     * @return Object
     */
    @GetMapping("/previewCode")
    public Object previewCode() {
        iGenerateService.previewCode();
        return null;
    }

}

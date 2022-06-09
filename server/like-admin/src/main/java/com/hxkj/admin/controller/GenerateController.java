package com.hxkj.admin.controller;

import com.hxkj.admin.service.IGenerateService;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/generate")
public class GenerateController {

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

}

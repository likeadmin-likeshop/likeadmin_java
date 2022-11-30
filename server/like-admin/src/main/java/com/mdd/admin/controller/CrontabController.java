package com.mdd.admin.controller;

import com.mdd.admin.service.ICrontabService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.CrontabListedVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping("api/crontab")
public class CrontabController {

    @Resource
    ICrontabService iCrontabService;

    /**
     * 计划任务列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return AjaxResult<Object>
     */
    @GetMapping("/list")
    public AjaxResult<Object> list(@Validated PageValidate pageValidate) {
        PageResult<CrontabListedVo> list = iCrontabService.list(pageValidate);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    public AjaxResult<Object> detail() {
        return AjaxResult.success();
    }

    @PostMapping("/add")
    public AjaxResult<Object> add() {
        return AjaxResult.success();
    }

    @PostMapping("/edit")
    public AjaxResult<Object> edit() {
        return AjaxResult.success();
    }

    @PostMapping("/del")
    public AjaxResult<Object> del() {
        return AjaxResult.success();
    }

}

package com.mdd.admin.controller;

import com.mdd.admin.service.ICrontabService;
import com.mdd.admin.validate.CrontabCreateValidate;
import com.mdd.admin.validate.CrontabUpdateValidate;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.CrontabDetailVo;
import com.mdd.admin.vo.CrontabListedVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/crontab")
public class CrontabController {

    @Resource
    ICrontabService iCrontabService;

    /**
     * 计划任务列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return AjaxResult< PageResult<CrontabListedVo>>
     */
    @GetMapping("/list")
    public AjaxResult< PageResult<CrontabListedVo>> list(@Validated PageValidate pageValidate) {
        PageResult<CrontabListedVo> list = iCrontabService.list(pageValidate);
        return AjaxResult.success(list);
    }

    /**
     * 计划任务详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<Object>
     */
    @GetMapping("/detail")
    public AjaxResult<Object> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        CrontabDetailVo vo = iCrontabService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 计划任务新增
     *
     * @author fzr
     * @param createValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/add")
    public AjaxResult<Object> add(@Validated @RequestBody CrontabCreateValidate createValidate) {
        iCrontabService.add(createValidate);
        return AjaxResult.success();
    }

    /**
     * 计划任务编辑
     *
     * @author fzr
     * @param updateValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody CrontabUpdateValidate updateValidate) {
        iCrontabService.edit(updateValidate);
        return AjaxResult.success();
    }

    /**
     * 计划任务删除
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/del")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iCrontabService.del(idValidate.getId());
        return AjaxResult.success();
    }

}

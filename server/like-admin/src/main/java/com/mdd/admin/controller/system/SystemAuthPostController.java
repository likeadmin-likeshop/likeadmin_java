package com.mdd.admin.controller.system;

import com.mdd.common.aop.NotPower;
import com.mdd.admin.service.ISystemAuthPostService;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.system.SystemPostCreateValidate;
import com.mdd.admin.validate.system.SystemPostSearchValidate;
import com.mdd.admin.validate.system.SystemPostUpdateValidate;
import com.mdd.admin.vo.system.SystemAuthPostVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/system/post")
@Api(tags = "系统岗位管理")
public class SystemAuthPostController {

    @Resource
    ISystemAuthPostService iSystemAuthPostService;

    @NotPower
    @GetMapping("/all")
    @ApiOperation(value="所有岗位")
    public AjaxResult<List<SystemAuthPostVo>> all() {
        List<SystemAuthPostVo> list = iSystemAuthPostService.all();
        return AjaxResult.success(list);
    }

    @GetMapping("/list")
    @ApiOperation(value="岗位列表")
    public AjaxResult<PageResult<SystemAuthPostVo>> list(@Validated PageValidate pageValidate,
                                                         @Validated SystemPostSearchValidate searchValidate) {
        PageResult<SystemAuthPostVo> list = iSystemAuthPostService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @ApiOperation(value="岗位详情")
    public AjaxResult<SystemAuthPostVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthPostVo vo = iSystemAuthPostService.detail(id);
        return AjaxResult.success(vo);
    }

    @PostMapping("/add")
    @ApiOperation(value="岗位新增")
    public AjaxResult<Object> add(@Validated @RequestBody SystemPostCreateValidate createValidate) {
        iSystemAuthPostService.add(createValidate);
        return AjaxResult.success();
    }

    @PostMapping("/edit")
    @ApiOperation(value="岗位编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody SystemPostUpdateValidate updateValidate) {
        iSystemAuthPostService.edit(updateValidate);
        return AjaxResult.success();
    }

    @PostMapping("/del")
    @ApiOperation(value="岗位删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iSystemAuthPostService.del(idValidate.getId());
        return AjaxResult.success();
    }

}

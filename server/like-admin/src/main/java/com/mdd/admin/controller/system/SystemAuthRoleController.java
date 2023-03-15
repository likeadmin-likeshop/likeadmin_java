package com.mdd.admin.controller.system;

import com.mdd.admin.aop.Log;
import com.mdd.common.aop.NotPower;
import com.mdd.admin.service.ISystemAuthRoleService;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.system.SystemRoleCreateValidate;
import com.mdd.admin.validate.system.SystemRoleUpdateValidate;
import com.mdd.admin.vo.system.SystemAuthRoleVo;
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
@RequestMapping("api/system/role")
@Api(tags = "系统角色管理")
public class SystemAuthRoleController {

    @Resource
    ISystemAuthRoleService iSystemAuthRoleService;

    @NotPower
    @GetMapping("/all")
    @ApiOperation(value="所有角色")
    public AjaxResult<List<SystemAuthRoleVo>> all() {
        List<SystemAuthRoleVo> list = iSystemAuthRoleService.all();
        return AjaxResult.success(list);
    }

    @Log(title = "角色列表")
    @GetMapping("/list")
    @ApiOperation(value="角色列表")
    public AjaxResult<PageResult<SystemAuthRoleVo>> list(@Validated PageValidate pageValidate) {
        PageResult<SystemAuthRoleVo> list = iSystemAuthRoleService.list(pageValidate);
        return AjaxResult.success(list);
    }

    @Log(title = "角色详情")
    @GetMapping("/detail")
    @ApiOperation(value="角色详情")
    public AjaxResult<SystemAuthRoleVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthRoleVo vo = iSystemAuthRoleService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "角色新增")
    @PostMapping("/add")
    @ApiOperation(value="角色新增")
    public AjaxResult<Object> add(@Validated @RequestBody SystemRoleCreateValidate createValidate) {
        iSystemAuthRoleService.add(createValidate);
        return AjaxResult.success();
    }

    @Log(title = "角色编辑")
    @PostMapping("/edit")
    @ApiOperation(value="角色编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody SystemRoleUpdateValidate updateValidate) {
        iSystemAuthRoleService.edit(updateValidate);
        return AjaxResult.success();
    }

    @Log(title = "角色删除")
    @PostMapping("/del")
    @ApiOperation(value="角色删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iSystemAuthRoleService.del(idValidate.getId());
        return AjaxResult.success();
    }

}

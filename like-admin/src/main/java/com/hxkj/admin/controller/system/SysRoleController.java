package com.hxkj.admin.controller.system;

import com.hxkj.admin.service.ISysRoleService;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.SysAdminParam;
import com.hxkj.admin.validate.SysRoleParam;
import com.hxkj.admin.vo.system.SysRoleListVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/system/role")
public class SysRoleController {

    @Resource
    ISysRoleService iSysRoleService;

    @GetMapping("/lists")
    public Object lists(@Validated PageParam pageParam) {
        PageResult<SysRoleListVo> lists = iSysRoleService.lists(pageParam);
        return AjaxResult.success(lists);
    }

    @GetMapping("/detail")
    public Object detail() {
        return null;
    }

    @PostMapping("/add")
    public Object add(@Validated(value = SysRoleParam.create.class) @RequestBody SysRoleParam sysRoleParam) {
        iSysRoleService.add(sysRoleParam);
        return AjaxResult.success();
    }

    @PostMapping("/edit")
    public Object edit(@Validated(value = SysRoleParam.create.class) @RequestBody SysRoleParam sysRoleParam) {
        iSysRoleService.edit(sysRoleParam);
        return AjaxResult.success();
    }

    @PostMapping("/del")
    public Object del(@Validated(value = SysRoleParam.create.class) @RequestBody SysRoleParam sysRoleParam) {
        iSysRoleService.del(sysRoleParam.getId());
        return AjaxResult.success();
    }

}

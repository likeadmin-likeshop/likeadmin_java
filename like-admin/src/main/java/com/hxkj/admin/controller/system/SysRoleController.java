package com.hxkj.admin.controller.system;

import com.hxkj.admin.service.ISysRoleService;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.SysRoleParam;
import com.hxkj.admin.vo.system.SysRoleVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/system/role")
public class SysRoleController {

    @Resource
    ISysRoleService iSysRoleService;

    /**
     * 角色列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return Object
     */
    @GetMapping("/lists")
    public Object lists(@Validated PageParam pageParam) {
        PageResult<SysRoleVo> lists = iSysRoleService.lists(pageParam);
        return AjaxResult.success(lists);
    }

    /**
     * 角色详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        return null;
    }

    /**
     * 新增角色
     *
     * @author fzr
     * @param sysRoleParam 角色参数
     * @return Object
     */
    @PostMapping("/add")
    public Object add(@Validated(value = SysRoleParam.create.class) @RequestBody SysRoleParam sysRoleParam) {
        iSysRoleService.add(sysRoleParam);
        return AjaxResult.success();
    }

    /**
     * 编辑角色
     *
     * @author fzr
     * @param sysRoleParam 角色参数
     * @return Object
     */
    @PostMapping("/edit")
    public Object edit(@Validated(value = SysRoleParam.create.class) @RequestBody SysRoleParam sysRoleParam) {
        iSysRoleService.edit(sysRoleParam);
        return AjaxResult.success();
    }

    /**
     * 删除角色
     *
     * @author fzr
     * @param id 角色ID
     * @return Object
     */
    @PostMapping("/del")
    public Object del(@Validated @IDMust() @RequestBody Integer id) {
        iSysRoleService.del(id);
        return AjaxResult.success();
    }

}

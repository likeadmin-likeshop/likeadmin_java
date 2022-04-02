package com.hxkj.admin.controller.system;

import com.hxkj.admin.config.aop.Log;
import com.hxkj.admin.service.ISystemRoleService;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.system.SystemRoleParam;
import com.hxkj.admin.vo.system.SystemRoleVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统角色管理
 */
@RestController
@RequestMapping("/api/system/role")
public class SystemRoleController {

    @Resource
    ISystemRoleService iSystemRoleService;

    /**
     * 角色列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return Object
     */
    @Log(title = "角色列表")
    @GetMapping("/lists")
    public Object lists(@Validated PageParam pageParam) {
        PageResult<SystemRoleVo> lists = iSystemRoleService.lists(pageParam);
        return AjaxResult.success(lists);
    }

    /**
     * 角色详情
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "角色详情")
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemRoleVo vo = iSystemRoleService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 新增角色
     *
     * @author fzr
     * @param systemRoleParam 角色参数
     * @return Object
     */
    @Log(title = "角色新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SystemRoleParam.create.class) @RequestBody SystemRoleParam systemRoleParam) {
        iSystemRoleService.add(systemRoleParam);
        return AjaxResult.success();
    }

    /**
     * 编辑角色
     *
     * @author fzr
     * @param systemRoleParam 角色参数
     * @return Object
     */
    @Log(title = "角色编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SystemRoleParam.create.class) @RequestBody SystemRoleParam systemRoleParam) {
        iSystemRoleService.edit(systemRoleParam);
        return AjaxResult.success();
    }

    /**
     * 删除角色
     *
     * @author fzr
     * @param systemRoleParam 角色参数
     * @return Object
     */
    @Log(title = "角色删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SystemRoleParam.delete.class) @RequestBody SystemRoleParam systemRoleParam) {
        iSystemRoleService.del(systemRoleParam.getId());
        return AjaxResult.success();
    }

}

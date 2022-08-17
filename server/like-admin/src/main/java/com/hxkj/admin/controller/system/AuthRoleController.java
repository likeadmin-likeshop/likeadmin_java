package com.hxkj.admin.controller.system;

import com.hxkj.admin.config.aop.Log;
import com.hxkj.admin.service.system.ISystemAuthRoleService;
import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.validate.system.SystemAuthRoleParam;
import com.hxkj.admin.vo.system.SystemAuthRoleVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统角色管理
 */
@RestController
@RequestMapping("api/system/role")
public class AuthRoleController {

    @Resource
    ISystemAuthRoleService iSystemAuthRoleService;

    /**
     * 角色所有
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/all")
    public Object all() {
        List<Map<String, Object>> list = iSystemAuthRoleService.all();
        return AjaxResult.success(list);
    }

    /**
     * 角色列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return Object
     */
    @Log(title = "角色列表")
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam) {
        PageResult<SystemAuthRoleVo> lists = iSystemAuthRoleService.list(pageParam);
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
        SystemAuthRoleVo vo = iSystemAuthRoleService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 新增角色
     *
     * @author fzr
     * @param systemAuthRoleParam 角色参数
     * @return Object
     */
    @Log(title = "角色新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SystemAuthRoleParam.create.class) @RequestBody SystemAuthRoleParam systemAuthRoleParam) {
        iSystemAuthRoleService.add(systemAuthRoleParam);
        return AjaxResult.success();
    }

    /**
     * 编辑角色
     *
     * @author fzr
     * @param systemAuthRoleParam 角色参数
     * @return Object
     */
    @Log(title = "角色编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SystemAuthRoleParam.create.class) @RequestBody SystemAuthRoleParam systemAuthRoleParam) {
        iSystemAuthRoleService.edit(systemAuthRoleParam);
        return AjaxResult.success();
    }

    /**
     * 删除角色
     *
     * @author fzr
     * @param systemAuthRoleParam 角色参数
     * @return Object
     */
    @Log(title = "角色删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SystemAuthRoleParam.delete.class) @RequestBody SystemAuthRoleParam systemAuthRoleParam) {
        iSystemAuthRoleService.del(systemAuthRoleParam.getId());
        return AjaxResult.success();
    }

}

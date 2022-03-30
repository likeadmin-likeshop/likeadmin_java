package com.hxkj.admin.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.hxkj.admin.LikeAdminThreadLocal;
import com.hxkj.admin.service.ISystemMenuService;
import com.hxkj.admin.validate.SysMenuParam;
import com.hxkj.admin.vo.system.SystemMenuVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/system/menu")
public class SystemMenuController {

    @Resource
    ISystemMenuService iSystemMenuService;

    /**
     * 获取系统菜单
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/menus")
    public Object menus() {
        Integer roleId = LikeAdminThreadLocal.getRoleId();
        JSONArray lists = iSystemMenuService.selectMenuByRoleId(roleId);
        return AjaxResult.success(lists);
    }

    /**
     * 获取菜单列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/lists")
    public Object lists() {
        JSONArray lists = iSystemMenuService.lists();
        return AjaxResult.success(lists);
    }

    /**
     * 获取菜单详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemMenuVo vo = iSystemMenuService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 新增菜单
     *
     * @author fzr
     * @return Object
     */
    @PostMapping("/add")
    public Object add(@Validated(value = SysMenuParam.create.class) @RequestBody SysMenuParam sysMenuParam) {
        iSystemMenuService.add(sysMenuParam);
        return AjaxResult.success();
    }

    /**
     * 更新菜单
     *
     * @author fzr
     * @return Object
     */
    @PostMapping("/edit")
    public Object edit(@Validated(value = SysMenuParam.update.class) @RequestBody SysMenuParam sysMenuParam) {
        iSystemMenuService.edit(sysMenuParam);
        return AjaxResult.success();
    }

    /**
     * 删除菜单
     *
     * @author fzr
     * @return Object
     */
    @PostMapping("/del")
    public Object del(@Validated(value = SysMenuParam.delete.class) @RequestBody SysMenuParam sysMenuParam) {
        iSystemMenuService.del(sysMenuParam.getId());
        return AjaxResult.success();
    }

}

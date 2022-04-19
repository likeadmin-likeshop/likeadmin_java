package com.hxkj.admin.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.hxkj.admin.LikeAdminThreadLocal;
import com.hxkj.admin.config.aop.Log;
import com.hxkj.admin.service.ISystemMenuService;
import com.hxkj.admin.validate.system.SystemMenuParam;
import com.hxkj.admin.vo.system.SystemAuthVo;
import com.hxkj.admin.vo.system.SystemMenuVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统菜单管理
 */
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
    @Log(title = "菜单新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SystemMenuParam.create.class) @RequestBody SystemMenuParam systemMenuParam) {
        iSystemMenuService.add(systemMenuParam);
        return AjaxResult.success();
    }

    /**
     * 更新菜单
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "菜单编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SystemMenuParam.update.class) @RequestBody SystemMenuParam systemMenuParam) {
        iSystemMenuService.edit(systemMenuParam);
        return AjaxResult.success();
    }

    /**
     * 删除菜单
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "菜单删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SystemMenuParam.delete.class) @RequestBody SystemMenuParam systemMenuParam) {
        iSystemMenuService.del(systemMenuParam.getId());
        return AjaxResult.success();
    }

}

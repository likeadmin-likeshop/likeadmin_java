package com.hxkj.admin.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.hxkj.admin.LikeAdminThreadLocal;
import com.hxkj.admin.config.aop.Log;
import com.hxkj.admin.service.system.ISystemAuthMenuService;
import com.hxkj.admin.validate.system.SystemAuthMenuParam;
import com.hxkj.admin.vo.system.SystemAuthMenuVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统菜单管理
 */
@RestController
@RequestMapping("api/system/menu")
public class AuthMenuController {

    @Resource
    ISystemAuthMenuService iSystemAuthMenuService;

    /**
     * 获取菜单路由
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/route")
    public Object route() {
        Integer roleId = LikeAdminThreadLocal.getRoleId();
        JSONArray lists = iSystemAuthMenuService.selectMenuByRoleId(roleId);
        return AjaxResult.success(lists);
    }

    /**
     * 获取菜单列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/list")
    public Object list() {
        JSONArray lists = iSystemAuthMenuService.list();
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
        SystemAuthMenuVo vo = iSystemAuthMenuService.detail(id);
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
    public Object add(@Validated(value = SystemAuthMenuParam.create.class) @RequestBody SystemAuthMenuParam systemAuthMenuParam) {
        iSystemAuthMenuService.add(systemAuthMenuParam);
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
    public Object edit(@Validated(value = SystemAuthMenuParam.update.class) @RequestBody SystemAuthMenuParam systemAuthMenuParam) {
        iSystemAuthMenuService.edit(systemAuthMenuParam);
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
    public Object del(@Validated(value = SystemAuthMenuParam.delete.class) @RequestBody SystemAuthMenuParam systemAuthMenuParam) {
        iSystemAuthMenuService.del(systemAuthMenuParam.getId());
        return AjaxResult.success();
    }

}

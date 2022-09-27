package com.mdd.admin.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.system.ISystemAuthMenuService;
import com.mdd.admin.validate.system.SystemAuthMenuParam;
import com.mdd.admin.vo.system.SystemAuthMenuVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.validator.annotation.IDMust;
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
     * @return AjaxResult
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
     * @return AjaxResult
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
     * @return AjaxResult
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
     * @return AjaxResult
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
     * @return AjaxResult
     */
    @Log(title = "菜单编辑")
    @PostMapping("/edit")
    public AjaxResult edit(@Validated(value = SystemAuthMenuParam.update.class) @RequestBody SystemAuthMenuParam systemAuthMenuParam) {
        iSystemAuthMenuService.edit(systemAuthMenuParam);
        return AjaxResult.success();
    }

    /**
     * 删除菜单
     *
     * @author fzr
     * @return AjaxResult
     */
    @Log(title = "菜单删除")
    @PostMapping("/del")
    public AjaxResult del(@Validated(value = SystemAuthMenuParam.delete.class) @RequestBody SystemAuthMenuParam systemAuthMenuParam) {
        iSystemAuthMenuService.del(systemAuthMenuParam.getId());
        return AjaxResult.success();
    }

}

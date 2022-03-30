package com.hxkj.admin.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.hxkj.admin.service.ISysMenuService;
import com.hxkj.admin.validate.SysMenuParam;
import com.hxkj.admin.vo.system.SysMenuVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.entity.system.SysMenu;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/system/menu")
public class SysMenuController {

    @Resource
    ISysMenuService iSysMenuService;

    /**
     * 获取菜单列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/lists")
    public Object lists() {
        JSONArray lists = iSysMenuService.lists();
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
        SysMenuVo vo = iSysMenuService.detail(id);
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
        iSysMenuService.add(sysMenuParam);
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
        iSysMenuService.edit(sysMenuParam);
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
        iSysMenuService.del(sysMenuParam.getId());
        return AjaxResult.success();
    }

}

package com.mdd.admin.controller.system;

import com.alibaba.fastjson2.JSONArray;
import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.ISystemAuthMenuService;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.system.SystemMenuCreateValidate;
import com.mdd.admin.validate.system.SystemMenuUpdateValidate;
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
public class SystemAuthMenuController {

    @Resource
    ISystemAuthMenuService iSystemAuthMenuService;

    /**
     * 获取菜单路由
     *
     * @author fzr
     * @return AjaxResult<JSONArray>
     */
    @GetMapping("/route")
    public AjaxResult<JSONArray> route() {
        Integer roleId = LikeAdminThreadLocal.getRoleId();
        JSONArray lists = iSystemAuthMenuService.selectMenuByRoleId(roleId);
        return AjaxResult.success(lists);
    }

    /**
     * 获取菜单列表
     *
     * @author fzr
     * @return AjaxResult<JSONArray>
     */
    @GetMapping("/list")
    public AjaxResult<JSONArray> list() {
        JSONArray lists = iSystemAuthMenuService.list();
        return AjaxResult.success(lists);
    }

    /**
     * 获取菜单详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<SystemAuthMenuVo>
     */
    @GetMapping("/detail")
    public AjaxResult<SystemAuthMenuVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthMenuVo vo = iSystemAuthMenuService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 新增菜单
     *
     * @author fzr
     * @param createValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "菜单新增")
    @PostMapping("/add")
    public AjaxResult<Object> add(@Validated @RequestBody SystemMenuCreateValidate createValidate) {
        iSystemAuthMenuService.add(createValidate);
        return AjaxResult.success();
    }

    /**
     * 更新菜单
     *
     * @author fzr
     * @param updateValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "菜单编辑")
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody SystemMenuUpdateValidate updateValidate) {
        iSystemAuthMenuService.edit(updateValidate);
        return AjaxResult.success();
    }

    /**
     * 删除菜单
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "菜单删除")
    @PostMapping("/del")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iSystemAuthMenuService.del(idValidate.getId());
        return AjaxResult.success();
    }

}

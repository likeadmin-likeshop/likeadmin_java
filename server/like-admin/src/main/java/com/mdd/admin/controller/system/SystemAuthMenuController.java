package com.mdd.admin.controller.system;

import com.alibaba.fastjson2.JSONArray;
import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.admin.aop.Log;
import com.mdd.common.aop.NotPower;
import com.mdd.admin.service.ISystemAuthMenuService;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.system.SystemMenuCreateValidate;
import com.mdd.admin.validate.system.SystemMenuUpdateValidate;
import com.mdd.admin.vo.system.SystemAuthMenuVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.validator.annotation.IDMust;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/system/menu")
@Api(tags = "系统菜单管理")
public class SystemAuthMenuController {

    @Resource
    ISystemAuthMenuService iSystemAuthMenuService;

    @NotPower
    @GetMapping("/route")
    @ApiOperation(value="获取菜单路由")
    public AjaxResult<JSONArray> route() {
        List<Integer> roleIds = LikeAdminThreadLocal.getRoleIds();
        JSONArray lists = iSystemAuthMenuService.selectMenuByRoleId(roleIds);
        return AjaxResult.success(lists);
    }

    @NotPower
    @GetMapping("/list")
    @ApiOperation(value="获取菜单列表")
    public AjaxResult<JSONArray> list() {
        JSONArray lists = iSystemAuthMenuService.list();
        return AjaxResult.success(lists);
    }

    @GetMapping("/detail")
    @ApiOperation(value="获取菜单详情")
    public AjaxResult<SystemAuthMenuVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthMenuVo vo = iSystemAuthMenuService.detail(id);
        return AjaxResult.success(vo);
    }

    @Log(title = "菜单新增")
    @PostMapping("/add")
    @ApiOperation(value="新增菜单")
    public AjaxResult<Object> add(@Validated @RequestBody SystemMenuCreateValidate createValidate) {
        iSystemAuthMenuService.add(createValidate);
        return AjaxResult.success();
    }

    @Log(title = "菜单编辑")
    @PostMapping("/edit")
    @ApiOperation(value="菜单编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody SystemMenuUpdateValidate updateValidate) {
        iSystemAuthMenuService.edit(updateValidate);
        return AjaxResult.success();
    }

    @Log(title = "菜单删除")
    @PostMapping("/del")
    @ApiOperation(value="菜单删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iSystemAuthMenuService.del(idValidate.getId());
        return AjaxResult.success();
    }

}

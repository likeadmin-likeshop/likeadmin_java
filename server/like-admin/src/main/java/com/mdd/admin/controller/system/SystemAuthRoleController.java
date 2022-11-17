package com.mdd.admin.controller.system;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.ISystemAuthRoleService;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.system.SystemRoleCreateValidate;
import com.mdd.admin.validate.system.SystemRoleUpdateValidate;
import com.mdd.admin.vo.system.SystemAuthRoleVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
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
public class SystemAuthRoleController {

    @Resource
    ISystemAuthRoleService iSystemAuthRoleService;

    /**
     * 角色所有
     *
     * @author fzr
     * @return AjaxResult<List<SystemAuthRoleVo>>
     */
    @GetMapping("/all")
    public AjaxResult<List<SystemAuthRoleVo>> all() {
        List<SystemAuthRoleVo> list = iSystemAuthRoleService.all();
        return AjaxResult.success(list);
    }

    /**
     * 角色列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return PageResult<SystemAuthRoleVo>
     */
    @Log(title = "角色列表")
    @GetMapping("/list")
    public AjaxResult<PageResult<SystemAuthRoleVo>> list(@Validated PageValidate pageValidate) {
        PageResult<SystemAuthRoleVo> list = iSystemAuthRoleService.list(pageValidate);
        return AjaxResult.success(list);
    }

    /**
     * 角色详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<SystemAuthRoleVo>
     */
    @Log(title = "角色详情")
    @GetMapping("/detail")
    public AjaxResult<SystemAuthRoleVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthRoleVo vo = iSystemAuthRoleService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 新增角色
     *
     * @author fzr
     * @param createValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "角色新增")
    @PostMapping("/add")
    public AjaxResult<Object> add(@Validated @RequestBody SystemRoleCreateValidate createValidate) {
        iSystemAuthRoleService.add(createValidate);
        return AjaxResult.success();
    }

    /**
     * 编辑角色
     *
     * @author fzr
     * @param updateValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "角色编辑")
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody SystemRoleUpdateValidate updateValidate) {
        iSystemAuthRoleService.edit(updateValidate);
        return AjaxResult.success();
    }

    /**
     * 删除角色
     *
     * @author fzr
     * @param idValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "角色删除")
    @PostMapping("/del")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        iSystemAuthRoleService.del(idValidate.getId());
        return AjaxResult.success();
    }

}

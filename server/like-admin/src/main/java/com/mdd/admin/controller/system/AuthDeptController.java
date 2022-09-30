package com.mdd.admin.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.mdd.admin.service.system.ISystemAuthDeptService;
import com.mdd.admin.validate.system.SystemAuthDeptParam;
import com.mdd.admin.vo.system.SystemAuthDeptVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统部门管理
 */
@RestController
@RequestMapping("api/system/dept")
public class AuthDeptController {

    @Resource
    ISystemAuthDeptService iSystemAuthDeptService;

    /**
     * 部门所有
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/all")
    public AjaxResult all() {
        List<SystemAuthDeptVo> vos = iSystemAuthDeptService.all();
        return AjaxResult.success(vos);
    }

    /**
     * 部门列表
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/list")
    public AjaxResult list(@RequestParam Map<String, String> params) {
        JSONArray list = iSystemAuthDeptService.list(params);
        return AjaxResult.success(list);
    }

    /**
     * 部门详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthDeptVo vo = iSystemAuthDeptService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 部门新增
     *
     * @author fzr
     * @param systemAuthDeptParam 参数
     * @return AjaxResult
     */
    @PostMapping("/add")
    public AjaxResult add(@Validated(value = SystemAuthDeptParam.create.class) @RequestBody SystemAuthDeptParam systemAuthDeptParam) {
        iSystemAuthDeptService.add(systemAuthDeptParam);
        return AjaxResult.success();
    }

    /**
     * 部门编辑
     *
     * @author fzr
     * @param systemAuthDeptParam 参数
     * @return AjaxResult
     */
    @PostMapping("/edit")
    public AjaxResult edit(@Validated(value = SystemAuthDeptParam.update.class) @RequestBody SystemAuthDeptParam systemAuthDeptParam) {
        iSystemAuthDeptService.edit(systemAuthDeptParam);
        return AjaxResult.success();
    }

    /**
     * 部门删除
     *
     * @author fzr
     * @param systemAuthDeptParam 参数
     * @return AjaxResult
     */
    @PostMapping("/del")
    public AjaxResult del(@Validated(value = SystemAuthDeptParam.delete.class) @RequestBody SystemAuthDeptParam systemAuthDeptParam) {
        iSystemAuthDeptService.del(systemAuthDeptParam.getId());
        return AjaxResult.success();
    }

}

package com.hxkj.admin.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.hxkj.admin.service.system.ISystemDeptService;
import com.hxkj.admin.validate.system.SystemDeptParam;
import com.hxkj.admin.vo.system.SystemDeptVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.validator.annotation.IDMust;
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
public class SystemDeptController {

    @Resource
    ISystemDeptService iSystemDeptService;

    /**
     * 部门所有
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/all")
    public Object all() {
        List<SystemDeptVo> vos = iSystemDeptService.all();
        return AjaxResult.success(vos);
    }

    /**
     * 部门列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@RequestParam Map<String, String> params) {
        JSONArray list = iSystemDeptService.list(params);
        return AjaxResult.success(list);
    }

    /**
     * 部门详情
     *
     * @author fzr
     * @param id 主键
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemDeptVo vo = iSystemDeptService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 部门新增
     *
     * @author fzr
     * @param systemDeptParam 参数
     * @return Object
     */
    @PostMapping("/add")
    public Object add(@Validated(value = SystemDeptParam.create.class) @RequestBody SystemDeptParam systemDeptParam) {
        iSystemDeptService.add(systemDeptParam);
        return AjaxResult.success();
    }

    /**
     * 部门编辑
     *
     * @author fzr
     * @param systemDeptParam 参数
     * @return Object
     */
    @PostMapping("/edit")
    public Object edit(@Validated(value = SystemDeptParam.update.class) @RequestBody SystemDeptParam systemDeptParam) {
        iSystemDeptService.edit(systemDeptParam);
        return AjaxResult.success();
    }

    /**
     * 部门删除
     *
     * @author fzr
     * @param systemDeptParam 参数
     * @return Object
     */
    @PostMapping("/del")
    public Object del(@Validated(value = SystemDeptParam.delete.class) @RequestBody SystemDeptParam systemDeptParam) {
        iSystemDeptService.del(systemDeptParam.getId());
        return AjaxResult.success();
    }

}

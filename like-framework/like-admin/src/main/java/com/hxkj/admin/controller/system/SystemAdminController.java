package com.hxkj.admin.controller.system;

import com.hxkj.admin.LikeAdminThreadLocal;
import com.hxkj.admin.config.aop.Log;
import com.hxkj.admin.service.ISystemAdminService;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.system.SystemAdminParam;
import com.hxkj.admin.vo.system.SystemAdminVo;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 系统管理员管理
 */
@RestController
@RequestMapping("/api/system/admin")
public class SystemAdminController {

    @Resource
    ISystemAdminService iSystemAdminService;

    /**
     * 管理员列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/lists")
    public Object lists(@Validated PageParam pageParam,
                        @RequestParam Map<String, String> params) {
        PageResult<SystemAdminVo> list = iSystemAdminService.lists(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 管理员信息
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/self")
    public Object self() {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        SystemAdminVo vo = iSystemAdminService.detail(adminId);
        return AjaxResult.success(vo);
    }

    /**
     * 管理员详情
     *
     * @author fzr
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAdminVo vo = iSystemAdminService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 新增管理员
     *
     * @author fzr
     * @param systemAdminParam 参数
     * @return Object
     */
    @Log(title = "管理员新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SystemAdminParam.create.class) @RequestBody SystemAdminParam systemAdminParam) {
        iSystemAdminService.add(systemAdminParam);
        return AjaxResult.success();
    }

    /**
     * 编辑管理员
     *
     * @author fzr
     * @param systemAdminParam 参数
     * @return Object
     */
    @Log(title = "管理员编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SystemAdminParam.update.class) @RequestBody SystemAdminParam systemAdminParam) {
        iSystemAdminService.edit(systemAdminParam);
        return AjaxResult.success();
    }

    /**
     * 删除管理员
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "管理员删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SystemAdminParam.delete.class) @RequestBody SystemAdminParam systemAdminParam) {
        iSystemAdminService.del(systemAdminParam.getId());
        return AjaxResult.success();
    }

}

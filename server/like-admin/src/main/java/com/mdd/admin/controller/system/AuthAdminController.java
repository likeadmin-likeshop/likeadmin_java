package com.mdd.admin.controller.system;

import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.system.ISystemAuthAdminService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.validate.system.SystemAuthAdminParam;
import com.mdd.admin.vo.system.SystemAuthAdminVo;
import com.mdd.admin.vo.system.SystemAuthSelfVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 系统管理员管理
 */
@RestController
@RequestMapping("api/system/admin")
public class AuthAdminController {

    @Resource
    ISystemAuthAdminService iSystemAuthAdminService;

    /**
     * 管理员列表
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/list")
    public AjaxResult list(@Validated PageParam pageParam,
                        @RequestParam Map<String, String> params) {
        PageResult<SystemAuthAdminVo> list = iSystemAuthAdminService.list(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 管理员信息
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/self")
    public AjaxResult self() {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        SystemAuthSelfVo vo = iSystemAuthAdminService.self(adminId);
        return AjaxResult.success(vo);
    }

    /**
     * 管理员详情
     *
     * @author fzr
     * @param id 主键ID
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthAdminVo vo = iSystemAuthAdminService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 管理员新增
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     * @return AjaxResult
     */
    @Log(title = "管理员新增")
    @PostMapping("/add")
    public AjaxResult add(@Validated(value = SystemAuthAdminParam.create.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        iSystemAuthAdminService.add(systemAuthAdminParam);
        return AjaxResult.success();
    }

    /**
     * 管理员编辑
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     * @return AjaxResult
     */
    @Log(title = "管理员编辑")
    @PostMapping("/edit")
    public AjaxResult edit(@Validated(value = SystemAuthAdminParam.update.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        iSystemAuthAdminService.edit(systemAuthAdminParam);
        return AjaxResult.success();
    }

    /**
     * 当前管理员更新
     *
     * @author fzr
     * @return AjaxResult
     */
    @Log(title = "管理员更新")
    @PostMapping("/upInfo")
    public AjaxResult upInfo(@Validated(value = SystemAuthAdminParam.upInfo.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        iSystemAuthAdminService.upInfo(systemAuthAdminParam, adminId);
        return AjaxResult.success();
    }

    /**
     * 管理员删除
     *
     * @author fzr
     * @return AjaxResult
     */
    @Log(title = "管理员删除")
    @PostMapping("/del")
    public AjaxResult del(@Validated(value = SystemAuthAdminParam.delete.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        iSystemAuthAdminService.del(systemAuthAdminParam.getId());
        return AjaxResult.success();
    }

    /**
     * 管理员状态切换
     *
     * @author fzr
     * @return AjaxResult
     */
    @Log(title = "管理员状态切换")
    @PostMapping("/disable")
    public AjaxResult disable(@Validated(value = SystemAuthAdminParam.delete.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        iSystemAuthAdminService.disable(systemAuthAdminParam.getId());
        return AjaxResult.success();
    }

}

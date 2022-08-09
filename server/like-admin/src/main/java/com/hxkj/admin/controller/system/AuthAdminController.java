package com.hxkj.admin.controller.system;

import com.hxkj.admin.LikeAdminThreadLocal;
import com.hxkj.admin.config.aop.Log;
import com.hxkj.admin.service.system.ISystemAuthAdminService;
import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.validate.system.SystemAuthAdminParam;
import com.hxkj.admin.vo.system.SystemAdminVo;
import com.hxkj.admin.vo.system.SystemSelfVo;
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
@RequestMapping("api/system/admin")
public class AuthAdminController {

    @Resource
    ISystemAuthAdminService iSystemAuthAdminService;

    /**
     * 管理员列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                        @RequestParam Map<String, String> params) {
        PageResult<SystemAdminVo> list = iSystemAuthAdminService.list(pageParam, params);
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
        SystemSelfVo vo = iSystemAuthAdminService.self(adminId);
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
        SystemAdminVo vo = iSystemAuthAdminService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 管理员新增
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     * @return Object
     */
    @Log(title = "管理员新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SystemAuthAdminParam.create.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        iSystemAuthAdminService.add(systemAuthAdminParam);
        return AjaxResult.success();
    }

    /**
     * 管理员编辑
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     * @return Object
     */
    @Log(title = "管理员编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SystemAuthAdminParam.update.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        iSystemAuthAdminService.edit(systemAuthAdminParam);
        return AjaxResult.success();
    }

    /**
     * 当前管理员更新
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "管理员更新")
    @PostMapping("/upInfo")
    public Object upInfo(@Validated(value = SystemAuthAdminParam.upInfo.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        iSystemAuthAdminService.upInfo(systemAuthAdminParam, adminId);
        return AjaxResult.success();
    }

    /**
     * 管理员删除
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "管理员删除")
    @PostMapping("/del")
    public Object del(@Validated(value = SystemAuthAdminParam.delete.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        iSystemAuthAdminService.del(systemAuthAdminParam.getId());
        return AjaxResult.success();
    }

    /**
     * 管理员状态切换
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "管理员状态切换")
    @PostMapping("/disable")
    public Object disable(@Validated(value = SystemAuthAdminParam.delete.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        iSystemAuthAdminService.disable(systemAuthAdminParam.getId());
        return AjaxResult.success();
    }

}

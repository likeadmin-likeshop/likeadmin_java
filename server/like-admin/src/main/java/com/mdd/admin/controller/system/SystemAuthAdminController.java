package com.mdd.admin.controller.system;

import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.ISystemAuthAdminService;
import com.mdd.admin.validate.commons.IdValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.system.SystemAdminCreateValidate;
import com.mdd.admin.validate.system.SystemAdminSearchValidate;
import com.mdd.admin.validate.system.SystemAdminUpInfoValidate;
import com.mdd.admin.validate.system.SystemAdminUpdateValidate;
import com.mdd.admin.vo.system.SystemAuthAdminDetailVo;
import com.mdd.admin.vo.system.SystemAuthAdminListedVo;
import com.mdd.admin.vo.system.SystemAuthAdminSelvesVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统管理员管理
 */
@RestController
@RequestMapping("api/system/admin")
public class SystemAuthAdminController {

    @Resource
    ISystemAuthAdminService iSystemAuthAdminService;

    /**
     * 管理员列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return AjaxResult<PageResult<SystemAuthAdminListedVo>>
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<SystemAuthAdminListedVo>> list(@Validated PageValidate pageValidate,
                                                                @Validated SystemAdminSearchValidate searchValidate) {
        PageResult<SystemAuthAdminListedVo> list = iSystemAuthAdminService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    /**
     * 管理员信息
     *
     * @author fzr
     * @return AjaxResult<SystemAuthSelfVo>
     */
    @GetMapping("/self")
    public AjaxResult<SystemAuthAdminSelvesVo> self() {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        SystemAuthAdminSelvesVo vo = iSystemAuthAdminService.self(adminId);
        return AjaxResult.success(vo);
    }

    /**
     * 管理员详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<SystemAuthAdminDetailVo>
     */
    @GetMapping("/detail")
    public AjaxResult<SystemAuthAdminDetailVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthAdminDetailVo vo = iSystemAuthAdminService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 管理员新增
     *
     * @author fzr
     * @param createValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "管理员新增")
    @PostMapping("/add")
    public AjaxResult<Object> add(@Validated @RequestBody SystemAdminCreateValidate createValidate) {
        iSystemAuthAdminService.add(createValidate);
        return AjaxResult.success();
    }

    /**
     * 管理员编辑
     *
     * @author fzr
     * @param updateValidate 参数
     * @return AjaxResult<Object>
     */
    @Log(title = "管理员编辑")
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody SystemAdminUpdateValidate updateValidate) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        iSystemAuthAdminService.edit(updateValidate, adminId);
        return AjaxResult.success();
    }

    /**
     * 当前管理员更新
     *
     * @author fzr
     * @return AjaxResult<Object>
     */
    @Log(title = "管理员更新")
    @PostMapping("/upInfo")
    public AjaxResult<Object> upInfo(@Validated @RequestBody SystemAdminUpInfoValidate upInfoValidate) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        iSystemAuthAdminService.upInfo(upInfoValidate, adminId);
        return AjaxResult.success();
    }

    /**
     * 管理员删除
     *
     * @author fzr
     * @return AjaxResult<Object>
     */
    @Log(title = "管理员删除")
    @PostMapping("/del")
    public AjaxResult<Object> del(@Validated @RequestBody IdValidate idValidate) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        iSystemAuthAdminService.del(idValidate.getId(), adminId);
        return AjaxResult.success();
    }

    /**
     * 管理员状态切换
     *
     * @author fzr
     * @return AjaxResult<Object>
     */
    @Log(title = "管理员状态")
    @PostMapping("/disable")
    public AjaxResult<Object> disable(@Validated @RequestBody IdValidate idValidate) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        iSystemAuthAdminService.disable(idValidate.getId(), adminId);
        return AjaxResult.success();
    }

}

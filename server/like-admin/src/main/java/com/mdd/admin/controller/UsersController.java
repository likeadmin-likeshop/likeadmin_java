package com.mdd.admin.controller;

import com.mdd.admin.service.IUsersService;
import com.mdd.admin.validate.UsersSearchValidate;
import com.mdd.admin.validate.UsersUpdateValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.user.UserVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户管理
 */
@RestController
@RequestMapping("api/user")
public class UsersController {

    @Resource
    IUsersService iUsersService;

    /**
     * 用户列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return AjaxResult<PageResult<UserVo>>
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<UserVo>> list(@Validated PageValidate pageValidate,
                                               @Validated UsersSearchValidate searchValidate) {
        PageResult<UserVo> list = iUsersService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    /**
     * 用户详情
     *
     * @author fzr
     * @param id 主键
     * @return AjaxResult<UserVo>
     */
    @GetMapping("/detail")
    public AjaxResult<UserVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        UserVo vo = iUsersService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 用户编辑
     *
     * @author fzr
     * @param updateValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody UsersUpdateValidate updateValidate) {
        iUsersService.edit(updateValidate);
        return AjaxResult.success();
    }

}

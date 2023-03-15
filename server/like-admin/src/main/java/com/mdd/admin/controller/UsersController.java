package com.mdd.admin.controller;

import com.mdd.admin.service.IUsersService;
import com.mdd.admin.validate.UsersSearchValidate;
import com.mdd.admin.validate.UsersUpdateValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.user.UserVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/user")
@Api(tags = "用户数据管理")
public class UsersController {

    @Resource
    IUsersService iUsersService;

    @GetMapping("/list")
    @ApiOperation(value="用户列表")
    public AjaxResult<PageResult<UserVo>> list(@Validated PageValidate pageValidate,
                                               @Validated UsersSearchValidate searchValidate) {
        PageResult<UserVo> list = iUsersService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    @GetMapping("/detail")
    @ApiOperation(value="用户详情")
    public AjaxResult<UserVo> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        UserVo vo = iUsersService.detail(id);
        return AjaxResult.success(vo);
    }

    @PostMapping("/edit")
    @ApiOperation(value="用户编辑")
    public AjaxResult<Object> edit(@Validated @RequestBody UsersUpdateValidate updateValidate) {
        iUsersService.edit(updateValidate);
        return AjaxResult.success();
    }

}

package com.mdd.front.controller;

import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.exception.LoginException;
import com.mdd.common.exception.OperateException;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IUserService;
import com.mdd.front.validate.users.UserForgetPwdValidate;
import com.mdd.front.validate.users.UserPhoneBindValidate;
import com.mdd.front.validate.users.UserPhoneMnpValidate;
import com.mdd.front.validate.users.UserChangePwdValidate;
import com.mdd.front.validate.users.UserUpdateValidate;
import com.mdd.front.vo.users.UserCenterVo;
import com.mdd.front.vo.users.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/user")
@Api(tags = "用户管理")
public class UserController {

    @Resource
    IUserService iUserService;

    @NotLogin
    @GetMapping("/center")
    @ApiOperation(value="个人中心")
    public AjaxResult<UserCenterVo> center() {
        Integer userId = LikeFrontThreadLocal.getUserId();
        if (userId == 0) {
            throw new OperateException("未登录", 1);
        }

        UserCenterVo vo = iUserService.center(userId);
        return AjaxResult.success(vo);
    }

    @GetMapping("/info")
    @ApiOperation(value="个人信息")
    public AjaxResult<UserInfoVo> info() {
        Integer userId = LikeFrontThreadLocal.getUserId();

        UserInfoVo vo = iUserService.info(userId);
        return AjaxResult.success(vo);
    }

    @PostMapping("/edit")
    @ApiOperation(value="编辑信息")
    public AjaxResult<Object> edit(@Validated @RequestBody UserUpdateValidate updateValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        iUserService.edit(updateValidate, userId);
        return AjaxResult.success();
    }

    @PostMapping("/changePwd")
    @ApiOperation(value="修改密码")
    public AjaxResult<Object> changePwd(@Validated @RequestBody UserChangePwdValidate passwordValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        iUserService.changePwd(passwordValidate.getPassword(), passwordValidate.getOldPassword(), userId);
        return AjaxResult.success();
    }

    @NotLogin
    @PostMapping("/forgotPwd")
    @ApiOperation(value="忘记密码")
    public AjaxResult<Object> forgotPwd(@Validated @RequestBody UserForgetPwdValidate userForgetPwdValidate) {
        String password = userForgetPwdValidate.getPassword();
        String mobile = userForgetPwdValidate.getMobile();
        String code = userForgetPwdValidate.getCode();

        iUserService.forgotPwd(password, mobile, code);
        return AjaxResult.success();
    }

    @PostMapping("/bindMobile")
    @ApiOperation(value="绑定手机")
    public AjaxResult<Object> bindMobile(@Validated @RequestBody UserPhoneBindValidate mobileValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();

        iUserService.bindMobile(mobileValidate, userId);
        return AjaxResult.success();
    }

    @PostMapping("/mnpMobile")
    @ApiOperation(value="微信手机号")
    public AjaxResult<Object> mnpMobile(@Validated @RequestBody UserPhoneMnpValidate mobileValidate) {
        iUserService.mnpMobile(mobileValidate.getCode().trim());
        return AjaxResult.success();
    }

}

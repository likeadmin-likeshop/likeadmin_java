package com.mdd.front.controller;

import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.IUserService;
import com.mdd.front.validate.users.UserForgetPwdValidate;
import com.mdd.front.validate.users.UserPhoneBindValidate;
import com.mdd.front.validate.users.UserPhoneMnpValidate;
import com.mdd.front.validate.users.UserChangePwdValidate;
import com.mdd.front.validate.users.UserUpdateValidate;
import com.mdd.front.vo.users.UserCenterVo;
import com.mdd.front.vo.users.UserInfoVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户管理表
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    @Resource
    IUserService iUserService;

    /**
     * 个人中心
     *
     * @author fzr
     * @return AjaxResult<UserCenterVo>
     */
    @GetMapping("/center")
    public AjaxResult<UserCenterVo> center() {
        Integer userId = LikeFrontThreadLocal.getUserId();
        UserCenterVo vo = iUserService.center(userId);
        return AjaxResult.success(vo);
    }

    /**
     * 个人信息
     *
     * @author fzr
     * @return AjaxResult<UserInfoVo>
     */
    @GetMapping("/info")
    public AjaxResult<UserInfoVo> info() {
        Integer userId = LikeFrontThreadLocal.getUserId();
        UserInfoVo vo = iUserService.info(userId);
        return AjaxResult.success(vo);
    }

    /**
     * 编辑信息
     *
     * @author fzr
     * @param updateValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/edit")
    public AjaxResult<Object> edit(@Validated @RequestBody UserUpdateValidate updateValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();
        iUserService.edit(updateValidate, userId);
        return AjaxResult.success();
    }

    /**
     * 修改密码
     *
     * @author fzr
     * @param passwordValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/changePwd")
    public AjaxResult<Object> changePwd(@Validated @RequestBody UserChangePwdValidate passwordValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();
        iUserService.changePwd(passwordValidate.getPassword(), passwordValidate.getOldPassword(), userId);
        return AjaxResult.success();
    }

    /**
     * 忘记密码
     *
     * @author fzr
     * @param userForgetPwdValidate 参数
     * @return AjaxResult<Object>
     */
    @NotLogin
    @PostMapping("/forgotPwd")
    public AjaxResult<Object> forgotPwd(@Validated @RequestBody UserForgetPwdValidate userForgetPwdValidate) {
        iUserService.forgotPwd(userForgetPwdValidate);
        return AjaxResult.success();
    }

    /**
     * 绑定手机号
     *
     * @author fzr
     * @param mobileValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/bindMobile")
    public AjaxResult<Object> bindMobile(@Validated @RequestBody UserPhoneBindValidate mobileValidate) {
        Integer userId = LikeFrontThreadLocal.getUserId();
        iUserService.bindMobile(mobileValidate, userId);
        return AjaxResult.success();
    }

    /**
     * 微信手机号
     *
     * @author fzr
     * @param mobileValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/mnpMobile")
    public AjaxResult<Object> mnpMobile(@Validated @RequestBody UserPhoneMnpValidate mobileValidate) {
        iUserService.mnpMobile(mobileValidate.getCode().trim());
        return AjaxResult.success();
    }

}

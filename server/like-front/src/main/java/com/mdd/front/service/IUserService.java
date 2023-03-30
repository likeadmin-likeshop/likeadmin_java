package com.mdd.front.service;

import com.mdd.front.validate.users.*;
import com.mdd.front.vo.user.UserCenterVo;
import com.mdd.front.vo.user.UserInfoVo;

/**
 * 用户服务接口类
 */
public interface IUserService {

    /**
     * 个人中心
     *
     * @author fzr
     * @param userId 用户ID
     * @param terminal 用户终端
     * @return UserCenterVo
     */
    UserCenterVo center(Integer userId, Integer terminal);

    /**
     * 个人信息
     *
     * @author fzr
     * @param userId 用户ID
     * @return UserInfoVo
     */
    UserInfoVo info(Integer userId);

    /**
     * 编辑信息
     *
     * @author fzr
     * @param updateValidate 参数
     * @param userId 用户ID
     */
    void edit(UserUpdateValidate updateValidate, Integer userId);

    /**
     * 修改密码
     *
     * @author fzr
     * @param password 新密码
     * @param oldPassword 旧密码
     * @param userId 用户ID
     */
    void changePwd(String password, String oldPassword, Integer userId);

    /**
     * 忘记密码
     *
     * @author fzr
     * @param password 新密码
     * @param mobile 手机号
     * @param code 验证码
     */
    void forgotPwd(String password, String mobile, String code);

    /**
     * 绑定手机
     *
     * @author fzr
     * @param mobileValidate 参数
     * @param userId 用户ID
     */
    void bindMobile(UserPhoneBindValidate mobileValidate, Integer userId);

    /**
     * 微信手机
     *
     * @author fzr
     * @param code 获取手机号的Code
     */
    void mnpMobile(String code);

    /**
     * 更新新用户信息
     *
     * @param newUserUpdateValidate 参数
     * @param userId 用户id
     */
    void updateNewUserInfo(NewUserUpdateValidate newUserUpdateValidate, Integer userId);

    /**
     * 绑定微信小程序
     *
     * @param bindMnpValidate 参数
     * @param userId 用户ID
     */
    void bindMnp(UserBindWechatValidate bindMnpValidate, Integer userId);

    /**
     * 绑定微信公众号
     *
     * @param bindOaValidate 参数
     * @param userId 用户ID
     */
    void bindOa(UserBindWechatValidate bindOaValidate, Integer userId);
}

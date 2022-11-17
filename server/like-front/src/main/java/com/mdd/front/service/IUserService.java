package com.mdd.front.service;

import com.mdd.front.validate.UserBindMobileValidate;
import com.mdd.front.validate.UserUpdateValidate;
import com.mdd.front.vo.users.UserCenterVo;
import com.mdd.front.vo.users.UserInfoVo;

/**
 * 用户服务接口类
 */
public interface IUserService {

    /**
     * 个人中心
     *
     * @author fzr
     * @param userId 用户
     * @return UserCenterVo
     */
    UserCenterVo center(Integer userId);

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
     * 绑定手机
     *
     * @author fzr
     * @param mobileValidate 参数
     * @param userId 用户ID
     */
    void bindMobile(UserBindMobileValidate mobileValidate, Integer userId);

    /**
     * 微信手机
     *
     * @author fzr
     * @param code 获取手机号的Code
     */
    void mnpMobile(String code);

}

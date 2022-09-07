package com.mdd.front.service;

import com.mdd.front.vo.user.UserCenterVo;
import com.mdd.front.vo.user.UserInfoVo;

import java.util.Map;

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

    void mnpMobile(Map<String, String> params);

}

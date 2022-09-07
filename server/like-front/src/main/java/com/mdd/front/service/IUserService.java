package com.mdd.front.service;

import com.mdd.front.vo.user.UserCenterVo;
import com.mdd.front.vo.user.UserInfoVo;

public interface IUserService {

    UserCenterVo center(Integer userId);

    UserInfoVo info(Integer userId);
}

package com.mdd.common.mapper.user;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.common.entity.user.UserAuth;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户授权Mapper
 */
@Mapper
public interface UserAuthMapper extends IBaseMapper<UserAuth> {
}

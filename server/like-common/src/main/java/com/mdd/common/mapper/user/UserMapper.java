package com.mdd.common.mapper.user;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.common.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 */
@Mapper
public interface UserMapper extends IBaseMapper<User> {
}

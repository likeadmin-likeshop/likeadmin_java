package com.hxkj.common.mapper.log;

import com.hxkj.common.basics.BaseMapper;
import com.hxkj.common.entity.log.LogOperate;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统操作日志
 */
@Mapper
public interface LogOperateMapper extends BaseMapper<LogOperate> {
}

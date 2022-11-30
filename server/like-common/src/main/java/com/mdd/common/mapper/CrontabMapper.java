package com.mdd.common.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.common.entity.Crontab;
import org.apache.ibatis.annotations.Mapper;

/**
 * 计划任务Mapper
 */
@Mapper
public interface CrontabMapper extends IBaseMapper<Crontab> {
}

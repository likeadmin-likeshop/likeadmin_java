package com.hxkj.common.basics;

import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * 基类Mapper
 * @param <T>
 */
@Mapper
public interface BaseMapper<T> extends MPJBaseMapper<T> {
}

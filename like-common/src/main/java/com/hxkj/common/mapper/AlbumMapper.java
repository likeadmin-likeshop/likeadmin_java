package com.hxkj.common.mapper;

import com.hxkj.common.core.basics.IBaseMapper;
import com.hxkj.common.entity.Album;
import org.apache.ibatis.annotations.Mapper;

/**
 * 相册
 */
@Mapper
public interface AlbumMapper extends IBaseMapper<Album> {
}

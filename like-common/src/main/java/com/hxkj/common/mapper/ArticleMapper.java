package com.hxkj.common.mapper;

import com.hxkj.common.core.basics.IBaseMapper;
import com.hxkj.common.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章
 */
@Mapper
public interface ArticleMapper extends IBaseMapper<Article> {
}

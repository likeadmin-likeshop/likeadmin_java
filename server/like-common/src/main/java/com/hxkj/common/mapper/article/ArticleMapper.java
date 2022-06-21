package com.hxkj.common.mapper.article;

import com.hxkj.common.core.basics.IBaseMapper;
import com.hxkj.common.entity.article.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章
 */
@Mapper
public interface ArticleMapper extends IBaseMapper<Article> {
}

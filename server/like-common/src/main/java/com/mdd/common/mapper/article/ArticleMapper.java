package com.mdd.common.mapper.article;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.common.entity.article.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章
 */
@Mapper
public interface ArticleMapper extends IBaseMapper<Article> {
}

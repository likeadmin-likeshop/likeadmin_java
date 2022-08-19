package com.mdd.common.mapper.article;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.common.entity.article.ArticleCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章分类
 */
@Mapper
public interface ArticleCategoryMapper extends IBaseMapper<ArticleCategory> {
}

package com.mdd.front.service.impl;

import com.mdd.common.mapper.article.ArticleCategoryMapper;
import com.mdd.common.mapper.article.ArticleMapper;
import com.mdd.front.service.IArticleService;
import com.mdd.front.vo.article.ArticleCateVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Resource
    ArticleMapper articleMapper;

    @Resource
    ArticleCategoryMapper articleCategoryMapper;

    @Override
    public List<ArticleCateVo> category() {
        return null;
    }

    @Override
    public Object list() {
        return null;
    }

    @Override
    public Object detail() {
        return null;
    }

}

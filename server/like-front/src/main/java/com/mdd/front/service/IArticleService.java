package com.mdd.front.service;

import com.mdd.front.vo.article.ArticleCateVo;

import java.util.List;

/**
 * 文章服务接口类
 */
public interface IArticleService {

    List<ArticleCateVo> category();

    Object list();

    Object detail();

}

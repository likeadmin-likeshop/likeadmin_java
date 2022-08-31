package com.mdd.front.service;

import com.mdd.common.core.PageResult;
import com.mdd.front.validate.PageParam;
import com.mdd.front.vo.article.ArticleCateVo;
import com.mdd.front.vo.article.ArticleDetailVo;
import com.mdd.front.vo.article.ArticleListVo;

import java.util.List;

/**
 * 文章服务接口类
 */
public interface IArticleService {

    /**
     * 文章分类
     *
     * @author fzr
     * @return List<ArticleCateVo>
     */
    List<ArticleCateVo> category();

    /**
     * 文章分类
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param cid 分类ID
     * @return PageResult<ArticleListVo>
     */
    PageResult<ArticleListVo> list(PageParam pageParam, Integer cid);

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 文章主键
     * @return ArticleDetailVo
     */
    ArticleDetailVo detail(Integer id);

}

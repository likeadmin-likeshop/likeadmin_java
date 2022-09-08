package com.mdd.front.service;

import com.mdd.common.core.PageResult;
import com.mdd.front.validate.PageParam;
import com.mdd.front.vo.article.ArticleCateVo;
import com.mdd.front.vo.article.ArticleCollectVo;
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
     * @param userId 用户ID
     * @return PageResult<ArticleListVo>
     */
    PageResult<ArticleListVo> list(PageParam pageParam, Integer cid, Integer userId);

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 文章主键
     * @param userId 用户ID
     * @return ArticleDetailVo
     */
    ArticleDetailVo detail(Integer id, Integer userId);

    /**
     * 文章收藏
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param userId 用户ID
     * @return PageResult<ArticleListVo>
     */
    PageResult<ArticleCollectVo> collect(PageParam pageParam, Integer userId);

    /**
     * 加入收藏
     *
     * @author fzr
     * @param articleId 文章ID
     * @param userId 用户ID
     */
    void addCollect(Integer articleId, Integer userId);

    /**
     * 取消收藏
     *
     * @author fzr
     * @param id 主键
     * @param userId 用户ID
     */
    void cancelCollect(Integer id, Integer userId);

}

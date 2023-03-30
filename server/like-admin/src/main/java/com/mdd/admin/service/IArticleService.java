package com.mdd.admin.service;

import com.mdd.admin.validate.article.ArticleCreateValidate;
import com.mdd.admin.validate.article.ArticleSearchValidate;
import com.mdd.admin.validate.article.ArticleUpdateValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.article.ArticleDetailVo;
import com.mdd.admin.vo.article.ArticleListedVo;
import com.mdd.common.core.PageResult;

/**
 * 文章服务接口类
 */
public interface IArticleService {

    /**
     * 文章列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<ArticleListVo>
     */
    PageResult<ArticleListedVo> list(PageValidate pageValidate, ArticleSearchValidate searchValidate);

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 主键ID
     */
    ArticleDetailVo detail(Integer id);

    /**
     * 文章新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    void add(ArticleCreateValidate createValidate);

    /**
     * 文章编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    void edit(ArticleUpdateValidate updateValidate);

    /**
     * 文章删除
     *
     * @author fzr
     * @param id 文章主键
     */
    void del(Integer id);

    /**
     * 文章状态
     *
     * @author fzr
     * @param id 文章主键
     */
    void change(Integer id);

}

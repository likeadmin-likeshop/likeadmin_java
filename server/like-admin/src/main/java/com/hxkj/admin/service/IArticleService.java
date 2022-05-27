package com.hxkj.admin.service;

import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.article.CategoryParam;
import com.hxkj.admin.validate.article.ArticleParam;
import com.hxkj.admin.vo.article.ArticleDetailVo;
import com.hxkj.admin.vo.article.ArticleListVo;
import com.hxkj.admin.vo.article.CategoryVo;
import com.hxkj.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 文章服务接口类
 */
public interface IArticleService {

    /**
     * 文章列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<ArticleListVo>
     */
    PageResult<ArticleListVo> articleList(PageParam pageParam, Map<String, String> params);

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 主键ID
     */
    ArticleDetailVo articleDetail(Integer id);

    /**
     * 文章新增
     *
     * @author fzr
     * @param articleParam 文章参数
     */
    void articleAdd(ArticleParam articleParam);

    /**
     * 文章编辑
     *
     * @author fzr
     * @param articleParam 文章参数
     */
    void articleEdit(ArticleParam articleParam);

    /**
     * 文章删除
     *
     * @author fzr
     * @param id 文章主键
     */
    void articleDel(Integer id);

    /**
     * 分类所有
     *
     * @author fzr
     * @return List<CategoryVo>
     */
    List<CategoryVo> cateAll();

    /**
     * 分类列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<CategoryVo>
     */
    PageResult<CategoryVo> cateList(PageParam pageParam, Map<String, String> params);

    /**
     * 分类详情
     *
     * @author fzr
     * @param id 分类ID
     */
    CategoryVo cateDetail(Integer id);

    /**
     * 分类新增
     *
     * @author fzr
     * @param articleCateParam 分类参数
     */
    void cateAdd(CategoryParam articleCateParam);

    /**
     * 分类编辑
     *
     * @author fzr
     * @param articleCateParam 分类参数
     */
    void cateEdit(CategoryParam articleCateParam);

    /**
     * 分类删除
     *
     * @author fzr
     * @param id 分类ID
     */
    void cateDel(Integer id);

}

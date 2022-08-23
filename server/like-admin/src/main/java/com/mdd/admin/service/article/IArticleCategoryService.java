package com.mdd.admin.service.article;

import com.mdd.admin.validate.article.CategoryParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.vo.article.ArticleCateVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 文章分类服接口务类
 */
public interface IArticleCategoryService {

    /**
     * 分类所有
     *
     * @author fzr
     * @return List<CategoryVo>
     */
    List<ArticleCateVo> all();

    /**
     * 分类列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<CategoryVo>
     */
    PageResult<ArticleCateVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 分类详情
     *
     * @author fzr
     * @param id 分类ID
     */
    ArticleCateVo detail(Integer id);

    /**
     * 分类新增
     *
     * @author fzr
     * @param categoryParam 分类参数
     */
    void add(CategoryParam categoryParam);

    /**
     * 分类编辑
     *
     * @author fzr
     * @param categoryParam 分类参数
     */
    void edit(CategoryParam categoryParam);

    /**
     * 分类删除
     *
     * @author fzr
     * @param id 分类ID
     */
    void del(Integer id);

    /**
     * 分类状态
     *
     * @author fzr
     * @param id 分类ID
     */
    void change(Integer id);

}

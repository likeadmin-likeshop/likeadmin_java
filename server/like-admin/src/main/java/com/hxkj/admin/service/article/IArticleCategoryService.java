package com.hxkj.admin.service.article;

import com.hxkj.admin.validate.article.ArticleCateParam;
import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.vo.common.article.ArticleCateVo;
import com.hxkj.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 文章分类服务类接口
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
     * @param articleCateParam 分类参数
     */
    void add(ArticleCateParam articleCateParam);

    /**
     * 分类编辑
     *
     * @author fzr
     * @param articleCateParam 分类参数
     */
    void edit(ArticleCateParam articleCateParam);

    /**
     * 分类删除
     *
     * @author fzr
     * @param id 分类ID
     */
    void del(Integer id);

}

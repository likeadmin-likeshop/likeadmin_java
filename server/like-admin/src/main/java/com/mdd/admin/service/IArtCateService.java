package com.mdd.admin.service;

import com.mdd.admin.validate.article.ArtCateCreateValidate;
import com.mdd.admin.validate.article.ArtCateUpdateValidate;
import com.mdd.admin.validate.article.ArtCateSearchValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.article.ArticleCateVo;
import com.mdd.common.core.PageResult;

import java.util.List;

/**
 * 文章分类服接口务类
 */
public interface IArtCateService {

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
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<CategoryVo>
     */
    PageResult<ArticleCateVo> list(PageValidate pageValidate, ArtCateSearchValidate searchValidate);

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
     * @param createValidate 参数
     */
    void add(ArtCateCreateValidate createValidate);

    /**
     * 分类编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    void edit(ArtCateUpdateValidate updateValidate);

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

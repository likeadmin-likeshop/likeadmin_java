package com.hxkj.admin.service.article.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxkj.admin.service.article.IArticleCategoryService;
import com.hxkj.admin.validate.article.ArticleCateParam;
import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.vo.common.article.ArticleCateVo;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.article.ArticleCategory;
import com.hxkj.common.mapper.article.ArticleCategoryMapper;
import com.hxkj.common.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文章分类服务实现类
 */
@Service
public class ArticleCategoryServiceImpl implements IArticleCategoryService {

    @Resource
    ArticleCategoryMapper articleCategoryMapper;

    /**
     * 分类所有
     *
     * @author fzr
     * @return List<CategoryVo>
     */
    @Override
    public List<ArticleCateVo> all() {
        QueryWrapper<ArticleCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "sort", "is_show", "create_time", "update_time")
                .eq("is_delete", 0);

        List<ArticleCategory> lists = articleCategoryMapper.selectList(queryWrapper);

        List<ArticleCateVo> vos = new ArrayList<>();
        for (ArticleCategory category : lists) {
            ArticleCateVo vo = new ArticleCateVo();
            BeanUtils.copyProperties(category, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(vo.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(vo.getUpdateTime()));
            vos.add(vo);
        }

        return vos;
    }

    /**
     * 分类列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<CategoryVo>
     */
    @Override
    public PageResult<ArticleCateVo> list(PageParam pageParam, Map<String, String> params) {
        Integer pageNo   = pageParam.getPageNo();
        Integer pageSize = pageParam.getPageSize();

        QueryWrapper<ArticleCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "sort", "is_show", "create_time", "update_time")
                .eq("is_delete", 0);

        articleCategoryMapper.setSearch(queryWrapper, params, new String[]{
                "like:name:str",
                "=:isShow@is_show:int"
        });

        IPage<ArticleCategory> iPage = articleCategoryMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);

        List<ArticleCateVo> list = new ArrayList<>();
        for (ArticleCategory category : iPage.getRecords()) {
            ArticleCateVo vo = new ArticleCateVo();
            BeanUtils.copyProperties(category, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(vo.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(vo.getUpdateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 分类详情
     *
     * @author fzr
     * @param id 分类ID
     * @return CategoryVo
     */
    @Override
    public ArticleCateVo detail(Integer id) {
        ArticleCategory model = articleCategoryMapper.selectOne(
                new QueryWrapper<ArticleCategory>()
                        .select(ArticleCategory.class, info->
                          !info.getColumn().equals("is_delete") &&
                          !info.getColumn().equals("delete_time"))
                        .eq("id", id)
                        .eq("is_delete", 0));

        Assert.notNull(model, "分类不存在");

        ArticleCateVo vo = new ArticleCateVo();
        BeanUtils.copyProperties(model, vo);
        vo.setCreateTime(TimeUtil.timestampToDate(model.getCreateTime()));
        vo.setUpdateTime(TimeUtil.timestampToDate(model.getUpdateTime()));

        return vo;
    }

    /**
     * 分类新增
     *
     * @author fzr
     * @param articleCateParam 分类参数
     */
    @Override
    public void add(ArticleCateParam articleCateParam) {
        ArticleCategory model = new ArticleCategory();
        model.setId(articleCateParam.getId());
        model.setName(articleCateParam.getName());
        model.setSort(articleCateParam.getSort());
        model.setCreateTime(TimeUtil.timestamp());
        model.setUpdateTime(TimeUtil.timestamp());
        articleCategoryMapper.insert(model);
    }

    /**
     * 文章编辑
     *
     * @author fzr
     * @param articleCateParam 分类参数
     */
    @Override
    public void edit(ArticleCateParam articleCateParam) {
        ArticleCategory model = articleCategoryMapper.selectOne(
                new QueryWrapper<ArticleCategory>()
                        .select(ArticleCategory.class, info->
                           !info.getColumn().equals("is_delete") &&
                           !info.getColumn().equals("delete_time"))
                        .eq("id", articleCateParam.getId())
                        .eq("is_delete", 0));

        Assert.notNull(model, "分类不存在");

        model.setName(articleCateParam.getName());
        model.setSort(articleCateParam.getSort());
        model.setUpdateTime(TimeUtil.timestamp());
        articleCategoryMapper.updateById(model);
    }

    /**
     * 分类参数
     *
     * @author fzr
     * @param id 分类ID
     */
    @Override
    public void del(Integer id) {
        ArticleCategory model = articleCategoryMapper.selectOne(
                new QueryWrapper<ArticleCategory>()
                        .select(ArticleCategory.class, info->
                          !info.getColumn().equals("is_delete") &&
                          !info.getColumn().equals("delete_time"))
                        .eq("id", id)
                        .eq("is_delete", 0));

        Assert.notNull(model, "分类不存在");

        model.setIsDelete(1);
        model.setDeleteTime(TimeUtil.timestamp());
        articleCategoryMapper.updateById(model);
    }


}

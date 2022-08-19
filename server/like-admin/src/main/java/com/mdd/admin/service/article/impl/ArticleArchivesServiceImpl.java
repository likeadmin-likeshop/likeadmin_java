package com.mdd.admin.service.article.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.admin.service.article.IArticleArchivesService;
import com.mdd.admin.validate.article.ArticleParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.vo.article.ArticleDetailVo;
import com.mdd.admin.vo.article.ArticleListVo;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.article.Article;
import com.mdd.common.entity.article.ArticleCategory;
import com.mdd.common.mapper.article.ArticleCategoryMapper;
import com.mdd.common.mapper.article.ArticleMapper;
import com.mdd.common.utils.StringUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 文章服务实现类
 */
@Service
public class ArticleArchivesServiceImpl implements IArticleArchivesService {

    @Resource
    ArticleMapper articleMapper;

    @Resource
    ArticleCategoryMapper articleCategoryMapper;

    /**
     * 文章列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<ArticleListVo>
     */
    @Override
    public PageResult<ArticleListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer pageNo   = pageParam.getPageNo();
        Integer pageSize = pageParam.getPageSize();

        MPJQueryWrapper<Article> mpjQueryWrapper = new MPJQueryWrapper<Article>()
                .selectAll(Article.class)
                .select("ac.name as category")
                .innerJoin("?_article_category ac ON ac.id=t.cid".replace("?_", GlobalConfig.tablePrefix))
                .eq("t.is_delete", 0)
                .orderByDesc(Arrays.asList("t.sort", "t.id"));

        articleMapper.setSearch(mpjQueryWrapper, params, new String[]{
                "like:title@t.title:str",
                "=:cid@t.cid:int",
                "=:isShow@t.is_show:int",
                "datetime:startTime-endTime@t.create_time:str"
        });

        IPage<ArticleListVo> iPage = articleMapper.selectJoinPage(
                new Page<>(pageNo, pageSize),
                ArticleListVo.class,
                mpjQueryWrapper);

        for (ArticleListVo vo : iPage.getRecords()) {
            vo.setImage(UrlUtil.toAbsoluteUrl(vo.getImage()));
            vo.setCreateTime(TimeUtil.timestampToDate(vo.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(vo.getUpdateTime()));
        }

        return PageResult.iPageHandle(iPage);
    }

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 主键ID
     */
    @Override
    public ArticleDetailVo detail(Integer id) {
        Article model = articleMapper.selectOne(
                new QueryWrapper<Article>()
                        .select(Article.class, info->
                          !info.getColumn().equals("is_delete") &&
                          !info.getColumn().equals("delete_time"))
                        .eq("id", id)
                        .eq("is_delete", 0));

        Assert.notNull(model, "文章不存在");

        ArticleDetailVo vo = new ArticleDetailVo();
        BeanUtils.copyProperties(model, vo);
        vo.setContent(StringUtil.isNull(model.getContent()) ? "" : model.getContent());
        vo.setImage(UrlUtil.toAbsoluteUrl(model.getImage()));
        vo.setCreateTime(TimeUtil.timestampToDate(model.getCreateTime()));
        vo.setUpdateTime(TimeUtil.timestampToDate(model.getUpdateTime()));

        return vo;
    }

    /**
     * 文章新增
     *
     * @author fzr
     * @param articleParam 文章参数
     */
    @Override
    public void add(ArticleParam articleParam) {
        Article model = new Article();
        model.setCid(articleParam.getCid());
        model.setTitle(articleParam.getTitle());
        model.setImage(UrlUtil.toRelativeUrl(articleParam.getImage()));
        model.setIntro(articleParam.getIntro());
        model.setContent(articleParam.getContent());
        model.setSort(articleParam.getSort());
        model.setIsShow(articleParam.getIsShow());
        model.setVisit(0);
        model.setCreateTime(TimeUtil.timestamp());
        model.setUpdateTime(TimeUtil.timestamp());
        articleMapper.insert(model);
    }

    /**
     * 文章编辑
     *
     * @author fzr
     * @param articleParam 文章参数
     */
    @Override
    public void edit(ArticleParam articleParam) {
        Article model = articleMapper.selectOne(
                new QueryWrapper<Article>()
                .eq("id", articleParam.getId())
                .eq("is_delete", 0));

        Assert.notNull(model, "文章不存在!");

        Assert.notNull(articleCategoryMapper.selectOne(
                new QueryWrapper<ArticleCategory>()
                .eq("id", articleParam.getCid())
                .eq("is_delete", 0)), "分类不存在");

        model.setCid(articleParam.getCid());
        model.setTitle(articleParam.getTitle());
        model.setImage(UrlUtil.toRelativeUrl(articleParam.getImage()));
        model.setIntro(articleParam.getIntro());
        model.setContent(articleParam.getContent());
        model.setIsShow(articleParam.getIsShow());
        model.setSort(articleParam.getSort());
        model.setUpdateTime(TimeUtil.timestamp());
        articleMapper.updateById(model);
    }

    /**
     * 文章删除
     *
     * @author fzr
     * @param id 文章ID
     */
    @Override
    public void del(Integer id) {
        Article article = articleMapper.selectOne(
                new QueryWrapper<Article>()
                        .eq("id", id)
                        .eq("is_delete", 0));

        Assert.notNull(article, "文章不存在!");

        article.setIsDelete(1);
        article.setDeleteTime(TimeUtil.timestamp());
        articleMapper.updateById(article);
    }

}

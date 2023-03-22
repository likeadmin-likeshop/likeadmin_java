package com.mdd.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.article.Article;
import com.mdd.common.entity.article.ArticleCategory;
import com.mdd.common.entity.article.ArticleCollect;
import com.mdd.common.mapper.article.ArticleCategoryMapper;
import com.mdd.common.mapper.article.ArticleCollectMapper;
import com.mdd.common.mapper.article.ArticleMapper;
import com.mdd.common.util.StringUtils;
import com.mdd.common.util.TimeUtils;
import com.mdd.common.util.UrlUtils;
import com.mdd.front.service.IArticleService;
import com.mdd.front.validate.article.ArticleSearchValidate;
import com.mdd.front.validate.common.PageValidate;
import com.mdd.front.vo.article.ArticleCateVo;
import com.mdd.front.vo.article.ArticleCollectVo;
import com.mdd.front.vo.article.ArticleDetailVo;
import com.mdd.front.vo.article.ArticleListedVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 文章服务实现类
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    @Resource
    ArticleMapper articleMapper;

    @Resource
    ArticleCategoryMapper articleCategoryMapper;

    @Resource
    ArticleCollectMapper articleCollectMapper;

    /**
     * 文章分类
     *
     * @author fzr
     * @return List<ArticleCateVo>
     */
    @Override
    public List<ArticleCateVo> category() {
        List<ArticleCategory> articleCateVos = articleCategoryMapper.selectList(
                new QueryWrapper<ArticleCategory>()
                    .select("id,name")
                    .eq("is_show", 1)
                    .eq("is_delete", 0)
                    .orderByDesc(Arrays.asList("sort", "id")));

        List<ArticleCateVo> list = new LinkedList<>();
        for (ArticleCategory category: articleCateVos) {
            ArticleCateVo vo = new ArticleCateVo();
            BeanUtils.copyProperties(category, vo);
            list.add(vo);
        }

        return list;
    }

    /**
     * 文章列表
     *
     * @author fzr
     * @param userId 用户ID
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<ArticleListVo>
     */
    @Override
    public PageResult<ArticleListedVo> list( Integer userId, PageValidate pageValidate, ArticleSearchValidate searchValidate) {
        Integer pageNo   = pageValidate.getPageNo();
        Integer pageSize = pageValidate.getPageSize();

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,title,image,intro,visit,create_time");
        queryWrapper.eq("is_delete", 0);
        queryWrapper.eq("is_show", 1);

        articleMapper.setSearch(queryWrapper, searchValidate, new String[]{
                "like:keyword@title:str"
        });

        if (StringUtils.isNotNull(searchValidate.getCid()) && searchValidate.getCid() > 0) {
            queryWrapper.eq("cid", searchValidate.getCid());
        }

        if (StringUtils.isNotNull(searchValidate.getSort())) {
            switch (searchValidate.getSort()) {
                case "hot": // 最热
                    queryWrapper.orderByDesc(Arrays.asList("visit", "id"));
                    break;
                case "new": // 最新
                    queryWrapper.orderByDesc("id");
                    break;
                default:    // 默认
                    queryWrapper.orderByDesc(Arrays.asList("sort", "id"));
            }
        }

        IPage<Article> iPage = articleMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);

        List<Integer> ids = new LinkedList<>();
        List<ArticleListedVo> list = new LinkedList<>();
        for (Article article : iPage.getRecords()) {
            ArticleListedVo vo = new ArticleListedVo();
            BeanUtils.copyProperties(article, vo);
            vo.setCollect(false);
            vo.setImage(UrlUtils.toAbsoluteUrl(article.getImage()));
            vo.setCreateTime(TimeUtils.timestampToDate(article.getCreateTime()));
            list.add(vo);

            ids.add(article.getId());
        }

        if (userId != null && userId > 0 && ids.size() > 0) {
            List<ArticleCollect> articleCollects = articleCollectMapper.selectList(
                    new QueryWrapper<ArticleCollect>()
                            .eq("user_id", userId)
                            .eq("is_delete", 0)
                            .in("article_id", ids));

            List<Integer> collects = new LinkedList<>();
            for (ArticleCollect c : articleCollects) {
                collects.add(c.getArticleId());
            }

            for (ArticleListedVo vo : list) {
                vo.setCollect(collects.contains(vo.getId()));
            }
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 文章主键
     * @param userId 用户ID
     * @return ArticleDetailVo
     */
    @Override
    public ArticleDetailVo detail(Integer id, Integer userId) {
        Article article = articleMapper.selectOne(new QueryWrapper<Article>()
                .select("id,title,image,intro,summary,visit,author,content,create_time")
                .eq("id", id)
                .eq("is_show", 1)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(article, "数据不存在!");

        ArticleCollect articleCollect = articleCollectMapper.selectOne(new QueryWrapper<ArticleCollect>()
                .eq("user_id", userId)
                .eq("article_id", article.getId())
                .eq("is_delete", 0)
                .last("limit 1"));

        ArticleDetailVo vo = new ArticleDetailVo();
        BeanUtils.copyProperties(article, vo);
        vo.setCollect(articleCollect != null);
        vo.setImage(UrlUtils.toAbsoluteUrl(article.getImage()));
        vo.setCreateTime(TimeUtils.timestampToDate(article.getCreateTime()));

        article.setVisit(article.getVisit() + 1);
        articleMapper.updateById(article);

        return vo;
    }

    /**
     * 收藏列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param userId 用户ID
     * @return PageResult<ArticleCollectVo>
     */
    @Override
    public PageResult<ArticleCollectVo> collect(PageValidate pageValidate, Integer userId) {
        Integer pageNo   = pageValidate.getPageNo();
        Integer pageSize = pageValidate.getPageSize();

        MPJQueryWrapper<ArticleCollect> mpjQueryWrapper = new MPJQueryWrapper<>();
        mpjQueryWrapper.select("t.id,t.article_id,a.title,a.image,a.intro,a.visit,a.create_time")
                .eq("t.user_id", userId)
                .eq("t.is_delete", 0)
                .eq("a.is_delete", 0)
                .orderByDesc("t.id")
                .innerJoin("?_article a ON a.id=t.article_id".replace("?_", GlobalConfig.tablePrefix));

        IPage<ArticleCollectVo> iPage = articleCollectMapper.selectJoinPage(
                new Page<>(pageNo, pageSize),
                ArticleCollectVo.class,
                mpjQueryWrapper);

        for (ArticleCollectVo vo : iPage.getRecords()) {
            vo.setImage(UrlUtils.toAbsoluteUrl(vo.getImage()));
            vo.setCreateTime(TimeUtils.timestampToDate(vo.getCreateTime()));
        }

        return PageResult.iPageHandle(iPage);
    }

    /**
     * 加入收藏
     *
     * @author fzr
     * @param articleId 主键
     * @param userId 用户ID
     */
    @Override
    public void addCollect(Integer articleId, Integer userId) {
        ArticleCollect articleCollect = articleCollectMapper.selectOne(
                new QueryWrapper<ArticleCollect>()
                    .eq("article_id", articleId)
                    .eq("user_id", userId)
                    .last("limit 1"));

        if (StringUtils.isNotNull(articleCollect)) {
            articleCollect.setIsDelete(0);
            articleCollect.setUpdateTime(System.currentTimeMillis() / 1000);
            articleCollectMapper.updateById(articleCollect);
        } else {
            ArticleCollect model = new ArticleCollect();
            model.setArticleId(articleId);
            model.setUserId(userId);
            model.setIsDelete(0);
            model.setCreateTime(System.currentTimeMillis() / 1000);
            model.setUpdateTime(System.currentTimeMillis() / 1000);
            articleCollectMapper.insert(model);
        }
    }

    /**
     * 取消收藏
     *
     * @author fzr
     * @param articleId 文章ID
     * @param userId 用户ID
     */
    @Override
    public void cancelCollect(Integer articleId, Integer userId) {
        ArticleCollect articleCollect = articleCollectMapper.selectOne(
                new QueryWrapper<ArticleCollect>()
                        .eq("article_id", articleId)
                        .eq("user_id", userId)
                        .eq("is_delete", 0)
                        .last("limit 1"));

        Assert.notNull(articleCollect, "收藏不存在!");

        articleCollect.setIsDelete(1);
        articleCollect.setUpdateTime(System.currentTimeMillis() / 1000);
        articleCollectMapper.updateById(articleCollect);
    }

}

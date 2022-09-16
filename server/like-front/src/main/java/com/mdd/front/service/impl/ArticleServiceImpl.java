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
import com.mdd.common.entity.server.Sys;
import com.mdd.common.mapper.article.ArticleCategoryMapper;
import com.mdd.common.mapper.article.ArticleCollectMapper;
import com.mdd.common.mapper.article.ArticleMapper;
import com.mdd.common.utils.StringUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.front.service.IArticleService;
import com.mdd.front.validate.PageParam;
import com.mdd.front.vo.article.ArticleCateVo;
import com.mdd.front.vo.article.ArticleCollectVo;
import com.mdd.front.vo.article.ArticleDetailVo;
import com.mdd.front.vo.article.ArticleListVo;
import net.sf.jsqlparser.statement.create.table.Index;
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
     * @param pageParam 分页参数
     * @param cid 分类ID
     * @param userId 用户ID
     * @return PageResult<ArticleListVo>
     */
    @Override
    public PageResult<ArticleListVo> list(PageParam pageParam, Integer cid, Integer userId) {
        Integer pageNo   = pageParam.getPageNo();
        Integer pageSize = pageParam.getPageSize();

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,title,image,intro,visit,create_time");
        queryWrapper.eq("is_delete", 0);
        queryWrapper.eq("is_show", 1);
        queryWrapper.orderByDesc(Arrays.asList("sort", "id"));
        if (cid > 0) {
            queryWrapper.eq("cid", cid);
        }

        IPage<Article> iPage = articleMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);

        List<Integer> ids = new LinkedList<>();
        List<ArticleListVo> list = new LinkedList<>();
        for (Article article : iPage.getRecords()) {
            ArticleListVo vo = new ArticleListVo();
            BeanUtils.copyProperties(article, vo);
            vo.setCollect(false);
            vo.setImage(UrlUtil.toAbsoluteUrl(article.getImage()));
            vo.setCreateTime(TimeUtil.timestampToDate(article.getCreateTime()));
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

            for (ArticleListVo vo : list) {
                vo.setCollect(collects.contains(vo.getId()));
            }
        }

        return  PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
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
        vo.setImage(UrlUtil.toAbsoluteUrl(article.getImage()));
        vo.setCreateTime(TimeUtil.timestampToDate(article.getCreateTime()));
        return vo;
    }

    /**
     * 收藏列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param userId 用户ID
     * @return PageResult<ArticleCollectVo>
     */
    @Override
    public PageResult<ArticleCollectVo> collect(PageParam pageParam, Integer userId) {
        Integer pageNo   = pageParam.getPageNo();
        Integer pageSize = pageParam.getPageSize();

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
            vo.setImage(UrlUtil.toAbsoluteUrl(vo.getImage()));
            vo.setCreateTime(TimeUtil.timestampToDate(vo.getCreateTime()));
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

        if (StringUtil.isNotNull(articleCollect)) {
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

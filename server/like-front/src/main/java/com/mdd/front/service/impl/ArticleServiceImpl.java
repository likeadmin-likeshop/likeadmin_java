package com.mdd.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.article.Article;
import com.mdd.common.entity.article.ArticleCategory;
import com.mdd.common.mapper.article.ArticleCategoryMapper;
import com.mdd.common.mapper.article.ArticleMapper;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.front.service.IArticleService;
import com.mdd.front.validate.PageParam;
import com.mdd.front.vo.article.ArticleCateVo;
import com.mdd.front.vo.article.ArticleDetailVo;
import com.mdd.front.vo.article.ArticleListVo;
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
     * @return PageResult<ArticleListVo>
     */
    @Override
    public PageResult<ArticleListVo> list(PageParam pageParam, Integer cid) {
        Integer pageNo   = pageParam.getPageNo();
        Integer pageSize = pageParam.getPageSize();

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,title,image,intro,visit,create_time");
        if (cid > 0) {
            queryWrapper.eq("cid", cid);
        }

        IPage<Article> iPage = articleMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);

        List<ArticleListVo> list = new LinkedList<>();
        for (Article article : iPage.getRecords()) {
            ArticleListVo vo = new ArticleListVo();
            BeanUtils.copyProperties(article, vo);
            vo.setImage(UrlUtil.toAbsoluteUrl(article.getImage()));
            vo.setCreateTime(TimeUtil.timestampToDate(article.getCreateTime()));
            list.add(vo);
        }

        return  PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 文章主键
     * @return ArticleDetailVo
     */
    @Override
    public ArticleDetailVo detail(Integer id) {
        Article article = articleMapper.selectOne(new QueryWrapper<Article>()
                .select("id,title,image,intro,summary,visit,author,content,create_time")
                .eq("id", id)
                .eq("is_show", 1)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(article, "数据不存在!");

        ArticleDetailVo vo = new ArticleDetailVo();
        BeanUtils.copyProperties(article, vo);
        vo.setImage(UrlUtil.toAbsoluteUrl(article.getImage()));
        vo.setCreateTime(TimeUtil.timestampToDate(article.getCreateTime()));
        return vo;
    }

}

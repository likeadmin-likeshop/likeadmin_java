package com.mdd.admin.service.decorate.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.service.decorate.IDecorateDataService;
import com.mdd.admin.vo.decorate.DecorateDataArticleVo;
import com.mdd.common.entity.article.Article;
import com.mdd.common.mapper.article.ArticleMapper;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 装修数据服务实现类
 */
@Service
public class DecorateDataServiceImpl implements IDecorateDataService {

    @Resource
    ArticleMapper articleMapper;

    /**
     * 获取文章数据
     *
     * @author fzr
     * @param limit 条数
     * @return List<DecorateArticleDataVo>
     */
    @Override
    public List<DecorateDataArticleVo> article(Integer limit) {

        List<Article> articles = articleMapper.selectList(new QueryWrapper<Article>()
                .eq("is_show", 1)
                .eq("is_delete", 0)
                .orderByDesc("id")
                .last("limit " + limit));

        List<DecorateDataArticleVo> list = new LinkedList<>();
        for (Article article : articles) {
            DecorateDataArticleVo vo = new DecorateDataArticleVo();
            BeanUtils.copyProperties(article, vo);
            vo.setImage(UrlUtil.toAbsoluteUrl(article.getImage()));
            vo.setCreateTime(TimeUtil.timestampToDate(article.getCreateTime()));
            list.add(vo);
        }

        return list;
    }
}

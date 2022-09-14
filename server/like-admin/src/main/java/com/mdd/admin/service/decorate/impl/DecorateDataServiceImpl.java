package com.mdd.admin.service.decorate.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.service.decorate.IDecorateDataService;
import com.mdd.common.entity.article.Article;
import com.mdd.common.mapper.article.ArticleMapper;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
     * @return List<Map<String, Object>>
     */
    @Override
    public List<Map<String, Object>> article(Integer limit) {
        List<Map<String, Object>> articleList = new LinkedList<>();
        List<Article> articles = articleMapper.selectList(new QueryWrapper<Article>()
                .eq("is_show", 1)
                .eq("is_delete", 0)
                .orderByDesc("id")
                .last("limit " + limit));

        for (Article article : articles) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", article.getId());
            map.put("title", article.getTitle());
            map.put("intro", article.getIntro());
            map.put("summary", article.getSummary());
            map.put("image", UrlUtil.toAbsoluteUrl(article.getImage()));
            map.put("author", article.getAuthor());
            map.put("visit", article.getVisit());
            map.put("createTime", TimeUtil.timestampToDate(article.getCreateTime()));
            articleList.add(map);
        }

        return articleList;
    }
}

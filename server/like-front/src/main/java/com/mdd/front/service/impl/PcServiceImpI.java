package com.mdd.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.entity.decorate.DecoratePage;
import com.mdd.common.entity.article.Article;
import com.mdd.common.entity.article.ArticleCategory;
import com.mdd.common.entity.article.ArticleCollect;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.decorate.DecoratePageMapper;
import com.mdd.common.mapper.article.ArticleCategoryMapper;
import com.mdd.common.mapper.article.ArticleCollectMapper;
import com.mdd.common.mapper.article.ArticleMapper;
import com.mdd.common.util.*;
import com.mdd.front.service.IPcService;
import com.mdd.front.vo.article.PcArticleCenterVo;
import com.mdd.front.vo.article.PcArticleDetailVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PcServiceImpI implements IPcService {

    @Resource
    DecoratePageMapper decoratePageMapper;

    @Resource
    ArticleCategoryMapper articleCategoryMapper;

    @Resource
    ArticleCollectMapper articleCollectMapper;

    @Resource
    ArticleMapper articleMapper;

    /**
     * 主页
     *
     * @author cjh
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> index() {
        Map<String,Object> indexData = new LinkedHashMap<>();
        DecoratePage decoratePage = decoratePageMapper.selectOne(
                new QueryWrapper<DecoratePage>()
                        .eq("id", 4)
                        .last("limit 1"));
        //全部资讯
        List<Article> articlesAll = articleMapper.selectList(new QueryWrapper<Article>()
                .eq("is_show", 1)
                .eq("is_delete", 0)
                .orderByDesc("sort")
                .orderByDesc("id")
                .last("limit 5"));
        List<Map<String, Object>> articlesAllList = new LinkedList<>();
        for (Article article : articlesAll) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", article.getId());
            map.put("title", article.getTitle());
            map.put("intro", article.getIntro());
            map.put("summary", article.getSummary());
            map.put("image", UrlUtils.toAbsoluteUrl(article.getImage()));
            map.put("visit", article.getVisit());
            map.put("author", article.getAuthor());
            map.put("sort", article.getSort());
            map.put("createTime", TimeUtils.timestampToDate(article.getCreateTime()));
            articlesAllList.add(map);
        }

        //最新资讯
        List<Article> articlesNew = articleMapper.selectList(new QueryWrapper<Article>()
                .eq("is_show", 1)
                .eq("is_delete", 0)
                .orderByDesc("id")
                .last("limit 7"));
        List<Map<String, Object>> articlesNewList = new LinkedList<>();
        for (Article article : articlesNew) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", article.getId());
            map.put("title", article.getTitle());
            map.put("intro", article.getIntro());
            map.put("summary", article.getSummary());
            map.put("image", UrlUtils.toAbsoluteUrl(article.getImage()));
            map.put("visit", article.getVisit());
            map.put("sort", article.getSort());
            map.put("author", article.getAuthor());
            map.put("createTime", TimeUtils.timestampToDate(article.getCreateTime()));
            articlesNewList.add(map);
        }

        //热门资讯
        List<Article> articlesHot = articleMapper.selectList(new QueryWrapper<Article>()
                .eq("is_show", 1)
                .eq("is_delete", 0)
                .orderByDesc("visit")
                .last("limit 7"));
        List<Map<String, Object>> articlesHostList = new LinkedList<>();
        for (Article article : articlesHot) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", article.getId());
            map.put("title", article.getTitle());
            map.put("intro", article.getIntro());
            map.put("summary", article.getSummary());
            map.put("image", UrlUtils.toAbsoluteUrl(article.getImage()));
            map.put("author", article.getAuthor());
            map.put("visit", article.getVisit());
            map.put("sort", article.getSort());
            map.put("createTime", TimeUtils.timestampToDate(article.getCreateTime()));
            articlesHostList.add(map);
        }

        indexData.put("pages", decoratePage.getPageData());
        indexData.put("all", articlesAllList);
        indexData.put("new", articlesNewList);
        indexData.put("hot", articlesHostList);
        return  indexData;
    }

    /**
     * 配置
     *
     * @author cjh
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> getConfig() {
        Map<String, Object> config = new LinkedHashMap<>();

        // 登录配置
        Map<String, Object> loginMap = new LinkedHashMap<>();
        Map<String, String> loginConfig = ConfigUtils.get("login");
        loginMap.put("loginWay", ListUtils.stringToListAsInt(loginConfig.getOrDefault("loginWay", ""), ","));
        loginMap.put("forceBindMobile", Integer.parseInt(loginConfig.getOrDefault("forceBindMobile", "0")));
        loginMap.put("openOtherAuth", Integer.parseInt(loginConfig.getOrDefault("openOtherAuth", "0")));
        loginMap.put("openAgreement", Integer.parseInt(loginConfig.getOrDefault("openAgreement", "0")));
        loginMap.put("autoLoginAuth", ListUtils.stringToListAsInt(loginConfig.getOrDefault("autoLoginAuth", ""), ","));

        // 网址信息
        Map<String, Object> websiteMap = new LinkedHashMap<>();
        Map<String, String> websiteConfig = ConfigUtils.get("website");
        String copyright = websiteConfig.getOrDefault("copyright", "[]");
        List<Map<String, String>> copyrightMap = ListUtils.stringToListAsMapStr(copyright);

        websiteMap.put("shopName", websiteConfig.getOrDefault("shopName", "LikeAdmin"));
        websiteMap.put("shopLogo", UrlUtils.toAbsoluteUrl(websiteConfig.getOrDefault("shopLogo", "")));
        websiteMap.put("pcDesc", websiteConfig.getOrDefault("pcDesc", ""));
        websiteMap.put("pcIco", UrlUtils.toAbsoluteUrl(websiteConfig.getOrDefault("pcIco", "")));
        websiteMap.put("pcKeywords", websiteConfig.getOrDefault("pcKeywords", ""));
        websiteMap.put("pcLogo", UrlUtils.toAbsoluteUrl(websiteConfig.getOrDefault("pcLogo", "")));
        websiteMap.put("pcTitle", websiteConfig.getOrDefault("pcTitle", ""));

        // 演示公众号和小程序二维码
        Map<String,String> qrCodeMap = new LinkedHashMap<>();
        qrCodeMap.put("mnp",UrlUtils.toAbsoluteUrl(ConfigUtils.get("mp_channel","qrCode")));
        qrCodeMap.put("oa",UrlUtils.toAbsoluteUrl(ConfigUtils.get("oa_channel","qrCode")));

        // 返回数据
        config.put("version", GlobalConfig.version);
        config.put("domain", UrlUtils.domain());
        config.put("login", loginMap);
        config.put("website", websiteMap);
        config.put("copyright",copyrightMap);
        config.put("qrcode",qrCodeMap);
        return config;
    }

    /**
     * 资讯中心
     *
     * @author fzr
     * @return List<PcArticleCenterVo>
     */
    @Override
    public List<PcArticleCenterVo> articleCenter() {
        List<ArticleCategory> articleCategoryList = articleCategoryMapper.selectList(
                new QueryWrapper<ArticleCategory>()
                    .eq("is_show", 1)
                    .eq("is_delete", 0)
                    .orderByDesc(Arrays.asList("sort", "id")));

        List<PcArticleCenterVo> list = new LinkedList<>();
        for (ArticleCategory articleCategory : articleCategoryList) {
            List<Article> articleList = articleMapper.selectList(new QueryWrapper<Article>()
                    .eq("cid", articleCategory.getId())
                    .eq("is_show", 1)
                    .eq("is_delete", 0)
                    .orderByDesc(Arrays.asList("sort", "id"))
                    .last("limit 10"));

            List<Map<String, Object>> articles = new LinkedList<>();
            for (Article article : articleList) {
                Map<String, Object> a = new LinkedHashMap<>();
                a.put("id", article.getId());
                a.put("title", article.getTitle());
                a.put("image", UrlUtils.toAbsoluteUrl(article.getImage()));
                articles.add(a);
            }

            PcArticleCenterVo vo = new PcArticleCenterVo();
            vo.setId(articleCategory.getId());
            vo.setName(articleCategory.getName());
            vo.setArticle(articles);
            list.add(vo);
        }

        return list;
    }

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 文章主键
     * @param userId 用户ID
     * @return PcArticleDetailVo
     */
    @Override
    public PcArticleDetailVo articleDetail(Integer id, Integer userId) {
        // 文章详情
        Article article = articleMapper.selectOne(new QueryWrapper<Article>()
                .select(Article.class, info->
                        !info.getColumn().equals("is_show") &&
                        !info.getColumn().equals("is_delete") &&
                        !info.getColumn().equals("delete_time"))
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        if (StringUtils.isNull(article)) {
            throw new OperateException("文章数据不存在!");
        }

        // 分类名称
        ArticleCategory articleCategory = articleCategoryMapper.selectOne(
                new QueryWrapper<ArticleCategory>()
                    .eq("id", article.getCid())
                    .eq("is_delete", 0));

        // 上一条记录
        Article prev = articleMapper.selectOne(new QueryWrapper<Article>()
                .select("id,title")
                .lt("id", id)
                .eq("is_delete", 0)
                .orderByDesc(Arrays.asList("sort", "id"))
                .last("limit 1"));

        // 下一条记录
        Article next = articleMapper.selectOne(new QueryWrapper<Article>()
                .select("id,title")
                .gt("id", id)
                .eq("is_delete", 0)
                .orderByDesc(Arrays.asList("sort", "id"))
                .last("limit 1"));

        // 是否收藏
        ArticleCollect collect = articleCollectMapper.selectOne(new QueryWrapper<ArticleCollect>()
                .eq("article_id", article.getId())
                .eq("user_id", userId)
                .eq("is_delete", 0)
                .last("limit 1"));

        // 最新资讯
        List<Article> news = articleMapper.selectList(new QueryWrapper<Article>()
                .select("id,title,image,create_time,update_time")
                .eq("cid", article.getCid())
                .eq("is_delete", 0)
                .orderByDesc("id")
                .last("limit 8"));

        List<Map<String, Object>> newsList = new LinkedList<>();
        for (Article newArticle : news) {
            Map<String, Object> newsMap = new LinkedHashMap<>();
            newsMap.put("id", newArticle.getId());
            newsMap.put("title", newArticle.getTitle());
            newsMap.put("image", UrlUtils.toAbsoluteUrl(newArticle.getImage()));
            newsMap.put("createTime", TimeUtils.timestampToDate(newArticle.getCreateTime()));
            newsMap.put("updateTime", TimeUtils.timestampToDate(newArticle.getUpdateTime()));
            newsList.add(newsMap);
        }

        // 处理数据
        PcArticleDetailVo vo = new PcArticleDetailVo();
        BeanUtils.copyProperties(article, vo);
        vo.setCreateTime(TimeUtils.timestampToDate(vo.getCreateTime()));
        vo.setUpdateTime(TimeUtils.timestampToDate(vo.getUpdateTime()));
        vo.setCategory(StringUtils.isNotNull(articleCategory) ? articleCategory.getName() : "");
        vo.setIsCollect(StringUtils.isNotNull(collect) ? 1 : 0);
        vo.setNews(newsList);
        vo.setPrev(null);
        vo.setNext(null);

        if (StringUtils.isNotNull(prev)) {
            Map<String, Object> prevMap = new LinkedHashMap<>();
            prevMap.put("id", prev.getId());
            prevMap.put("title", prev.getTitle());
            vo.setPrev(prevMap);
        }

        if (StringUtils.isNotNull(next)) {
            Map<String, Object> nextMap = new LinkedHashMap<>();
            nextMap.put("id", next.getId());
            nextMap.put("title", next.getTitle());
            vo.setNext(nextMap);
        }

        return vo;
    }
}

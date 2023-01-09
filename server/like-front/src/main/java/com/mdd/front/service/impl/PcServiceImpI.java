package com.mdd.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.entity.DecoratePage;
import com.mdd.common.entity.article.Article;
import com.mdd.common.mapper.DecoratePageMapper;
import com.mdd.common.mapper.article.ArticleMapper;
import com.mdd.common.util.ArrayUtils;
import com.mdd.common.util.ConfigUtils;
import com.mdd.common.util.TimeUtils;
import com.mdd.common.util.UrlUtils;
import com.mdd.front.service.IPcService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class PcServiceImpI implements IPcService {

    @Resource
    DecoratePageMapper decoratePageMapper;

    @Resource
    ArticleMapper articleMapper;

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

    @Override
    public Map<String, Object> getConfig() {
        Map<String, Object> config = new LinkedHashMap<>();
        // 登录配置
        Map<String, Object> loginMap = new LinkedHashMap<>();
        Map<String, String> loginConfig = ConfigUtils.get("login");
        loginMap.put("loginWay", ArrayUtils.stringToListAsInt(loginConfig.getOrDefault("loginWay", ""), ","));
        loginMap.put("forceBindMobile", Integer.parseInt(loginConfig.getOrDefault("forceBindMobile", "0")));
        loginMap.put("openOtherAuth", Integer.parseInt(loginConfig.getOrDefault("openOtherAuth", "0")));
        loginMap.put("openAgreement", Integer.parseInt(loginConfig.getOrDefault("openAgreement", "0")));
        loginMap.put("autoLoginAuth", ArrayUtils.stringToListAsInt(loginConfig.getOrDefault("autoLoginAuth", ""), ","));

        // 网址信息
        Map<String, Object> websiteMap = new LinkedHashMap<>();
        Map<String, String> websiteConfig = ConfigUtils.get("website");
        String copyright = websiteConfig.getOrDefault("copyright", "[]");
        Map<String, String> copyrightMap = ArrayUtils.stringToListAsMapStr(copyright).get(0);

        websiteMap.put("shopName", websiteConfig.getOrDefault("shopName", "LikeAdmin"));
        websiteMap.put("shopLogo", UrlUtils.toAbsoluteUrl(websiteConfig.getOrDefault("shopLogo", "")));
        websiteMap.put("pcDesc", websiteConfig.getOrDefault("pcDesc", ""));
        websiteMap.put("pcIco", UrlUtils.toAbsoluteUrl(websiteConfig.getOrDefault("pcIco", "")));
        websiteMap.put("pcKeywords", websiteConfig.getOrDefault("pcKeywords", ""));
        websiteMap.put("pcLogo", UrlUtils.toAbsoluteUrl(websiteConfig.getOrDefault("pcLogo", "")));
        websiteMap.put("pcTitle", websiteConfig.getOrDefault("pcTitle", ""));

        //演示公众号和小程序二维码
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
}

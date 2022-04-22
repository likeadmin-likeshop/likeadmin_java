package com.hxkj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hxkj.admin.service.IIndexService;
import com.hxkj.common.entity.Article;
import com.hxkj.common.mapper.ArticleMapper;
import com.hxkj.common.utils.ConfigUtil;
import com.hxkj.common.utils.TimeUtil;
import com.hxkj.common.utils.UrlUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 主页服务实现类
 */
@Service
public class IndexServiceImpl implements IIndexService {

    @Resource
    ArticleMapper articleMapper;

    /**
     * 控制台数据
     *
     * @author fzr
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> console() {
        Map<String, Object> console = new LinkedHashMap<>();

        // 账号信息
        Map<String, Object> version = new LinkedHashMap<>();
        version.put("version", "1.0.0");
        version.put("website", "likeshop");
        console.put("version", version);

        // 今日数据
        Map<String, Object> today = new LinkedHashMap<>();
        today.put("todayVisits", 10);
        today.put("totalVisits", 100);
        today.put("todaySales", 30);
        today.put("totalSales", 65);
        today.put("todayUsers", 120);
        today.put("totalUsers", 360);
        console.put("today", today);

        // 访客图表
        Map<String, Object> visitor = new LinkedHashMap<>();
        visitor.put("date", TimeUtil.daysAgoDate(8));
        visitor.put("list", new ArrayList<>());
        console.put("visitor", visitor);

        // 文章排名
        List<Map<String, Object>> articles = articleMapper.selectMaps(new QueryWrapper<Article>()
                        .select("id", "title", "visit")
                        .eq("is_delete", 0)
                        .orderByDesc(Arrays.asList("visit", "id"))
                        .last("limit 10"));
        console.put("article", articles);

        return console;
    }

    /**
     * 公共配置
     *
     * @author fzr
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> config() {
        Map<String, String> website   = ConfigUtil.get("website");
        Map<String, String> copyright = ConfigUtil.get("copyright");

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("lawPrivilege", copyright.getOrDefault("privilege", ""));
        map.put("lawIcpNumber", copyright.getOrDefault("icpNumber", ""));
        map.put("lawIcpLink", copyright.getOrDefault("icpLink", ""));
        map.put("lawGaNumber", copyright.getOrDefault("gaNumber", ""));
        map.put("lawGaLink", copyright.getOrDefault("gaLink", ""));

        map.put("webName", website.getOrDefault("name", ""));
        map.put("webLogo", UrlUtil.toAbsoluteUrl(website.getOrDefault("logo", "")));
        map.put("webFavicon", UrlUtil.toAbsoluteUrl(website.getOrDefault("favicon", "")));
        map.put("webBackdrop", UrlUtil.toAbsoluteUrl(website.getOrDefault("backdrop", "")));

        map.put("ossDomain", UrlUtil.domain());

        return map;
    }

}

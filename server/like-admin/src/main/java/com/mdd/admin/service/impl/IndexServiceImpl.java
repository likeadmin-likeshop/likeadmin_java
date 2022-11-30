package com.mdd.admin.service.impl;

import com.mdd.admin.service.IIndexService;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 主页服务实现类
 */
@Service
public class IndexServiceImpl implements IIndexService {

    /**
     * 控制台数据
     *
     * @author fzr
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> console() {
        Map<String, Object> console = new LinkedHashMap<>();

        // 版本信息
        Map<String, Object> version = new LinkedHashMap<>();
        version.put("name", ConfigUtil.get("website", "name", "LikeAdmin-Java"));
        version.put("version", GlobalConfig.version);
        version.put("website", "www.likeadmin.cn");
        version.put("based", "Vue3.x、ElementUI、MySQL");
        Map<String, String> channel = new LinkedHashMap<>();
        channel.put("gitee", "https://gitee.com/likeadmin/likeadmin_java");
        channel.put("website", "https://www.likeadmin.cn");
        version.put("channel", channel);
        console.put("version", version);

        // 今日数据
        Map<String, Object> today = new LinkedHashMap<>();
        today.put("time", "2022-08-11 15:08:29");
        today.put("todayVisits", 10);   // 访问量(人)
        today.put("totalVisits", 100);  // 总访问量
        today.put("todaySales", 30);    // 销售额(元)
        today.put("totalSales", 65);    // 总销售额
        today.put("todayOrder", 12);    // 订单量(笔)
        today.put("totalOrder", 255);   // 总订单量
        today.put("todayUsers", 120);   // 新增用户
        today.put("totalUsers", 360);   // 总访用户
        console.put("today", today);

        // 访客图表
        Map<String, Object> visitor = new LinkedHashMap<>();
        visitor.put("date", TimeUtil.daysAgoDate(15));
        visitor.put("list", Arrays.asList(12,13,11,5,8,22,14,9,456,62,78,12,18,22,46));
        console.put("visitor", visitor);

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
        String copyright = ConfigUtil.get("website", "copyright", "");

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("webName", website.getOrDefault("name", ""));
        map.put("webLogo", UrlUtil.toAbsoluteUrl(website.getOrDefault("logo", "")));
        map.put("webFavicon", UrlUtil.toAbsoluteUrl(website.getOrDefault("favicon", "")));
        map.put("webBackdrop", UrlUtil.toAbsoluteUrl(website.getOrDefault("backdrop", "")));
        map.put("ossDomain", UrlUtil.domain());
        map.put("copyright", ArrayUtil.stringToListAsMapStr(copyright));

        return map;
    }

}

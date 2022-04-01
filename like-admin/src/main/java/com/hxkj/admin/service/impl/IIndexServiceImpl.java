package com.hxkj.admin.service.impl;

import com.hxkj.admin.service.IIndexService;
import com.hxkj.common.utils.TimeUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IIndexServiceImpl implements IIndexService {

    @Override
    public void console() {
        Map<String, Object> console = new LinkedHashMap<>();

        // 账号信息
        Map<String, Object> version = new LinkedHashMap<>();
        version.put("version", "1.0.0");
        version.put("website", "www.likeshop.cn");
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
        System.out.println(TimeUtil.daysAgoTime(8));

    }

}

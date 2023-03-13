package com.mdd.admin.controller.monitor;


import com.mdd.admin.aop.Log;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.util.StringUtils;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * 缓存监控管理
 */
@RestController
@RequestMapping("api/monitor")
public class MonitorCacheController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 缓存监控
     *
     * @author fzr
     * @return AjaxResult<Object>
     */
    @Log(title = "缓存监控")
    @GetMapping("/cache")
    public AjaxResult<Object> info() {
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) RedisServerCommands::info);
        Properties commandStats = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info("commandstats"));
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) RedisServerCommands::dbSize);

        if (commandStats == null) {
            return AjaxResult.failed("获取异常");
        }

        Map<String, Object> result = new HashMap<>(3);
        result.put("info", info);
        result.put("dbSize", dbSize);

        List<Map<String, String>> pieList = new ArrayList<>();
        commandStats.stringPropertyNames().forEach(key -> {
            Map<String, String> data = new HashMap<>(2);
            String property = commandStats.getProperty(key);
            data.put("name", StringUtils.removeStart(key, "cmdstat_"));
            data.put("value", StringUtils.substringBetween(property, "calls=", ",usec"));
            pieList.add(data);
        });

        result.put("commandStats", pieList);
        return AjaxResult.success(result);
    }

}

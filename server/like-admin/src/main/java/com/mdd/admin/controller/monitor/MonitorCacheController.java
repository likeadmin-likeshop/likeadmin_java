package com.mdd.admin.controller.monitor;


import com.mdd.admin.aop.Log;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("api/monitor")
@Api(tags = "监控缓存管理")
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
    @ApiOperation(value="缓存监控")
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

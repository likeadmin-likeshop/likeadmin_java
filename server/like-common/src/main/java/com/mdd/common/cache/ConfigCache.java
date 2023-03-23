package com.mdd.common.cache;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.entity.system.SystemConfig;
import com.mdd.common.mapper.system.SystemConfigMapper;
import com.mdd.common.util.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统配置缓存
 */
public class ConfigCache {

    private static final String cacheKey = "config";

    /**
     * 设置缓存配置
     */
    public static void set() {
        SystemConfigMapper model = SpringUtils.getBean(SystemConfigMapper.class);
        // 查询全部配置项，设置缓存
        List<SystemConfig> configs = model.selectList(
                new QueryWrapper<SystemConfig>()
                        .select("id", "type", "name", "value")
        );
        List<String> typeList = configs.stream().map(SystemConfig::getType)
                .collect(Collectors.toList())
                .stream().distinct()
                .collect(Collectors.toList());

        Map<String, Object> map = new LinkedHashMap<>();
        Map<String, Object> subMap = new LinkedHashMap<>();
        for (String typeItem : typeList) {
            subMap.clear();
            for (SystemConfig configItem : configs) {
                if (configItem.getType().equals(typeItem)) {
                    subMap.put(configItem.getName(), configItem.getValue());
                }
            }
            map.put(typeItem, JSON.toJSONString(subMap));
        }
        RedisUtils.set(cacheKey, JSON.toJSONString(map));
    }

    /**
     * 获取配置缓存 返回字符串
     *
     * @param type 类型
     * @param name 名称
     * @return String
     */
    public static String get(String type, String name) {
        Object configs = RedisUtils.get(cacheKey);
        if (!StringUtils.isNull(configs) && !StringUtils.isEmpty(configs.toString())) {
            Map<String, String> configType = MapUtils.jsonToMap(configs.toString());
            if (!StringUtils.isEmpty(configType)) {
                Map<String, String> configData = MapUtils.jsonToMap(configType.get(type));
                return configData.get(name);
            }
        }
        return null;
    }

    /**
     * 根据type获取配置缓存 返回map
     *
     * @param type 类型
     * @return Map
     */
    public static Map<String, String> get(String type) {
        // 读取缓存
        Map<String, Object> cacheConfig = getConfigCache();
        if (!cacheConfig.isEmpty()) {
            Object cacheConfigObj = cacheConfig.get(type);
            Map<String, String> cacheConfigMap = MapUtils.jsonToMap(cacheConfigObj.toString());
            if (!cacheConfigMap.isEmpty()) {
                return cacheConfigMap;
            }
        }
        return new LinkedHashMap<>();
    }

    /**
     * 获取配置
     *
     * @return Map
     */
    public static Map<String, Object> getConfigCache() {
        // 获取缓存
        Object config = RedisUtils.get(cacheKey);
        if (!StringUtils.isNull(config) && !StringUtils.isEmpty(config.toString())) {
            return MapUtils.jsonToMapAsObj(config.toString());
        }
        return new LinkedHashMap<>();
    }

    /**
     * 清除缓存
     */
    public static void clear() {
        RedisUtils.del(cacheKey);
    }

}

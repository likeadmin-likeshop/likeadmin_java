package com.mdd.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.cache.ConfigCache;
import com.mdd.common.entity.system.SystemConfig;
import com.mdd.common.mapper.system.SystemConfigMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库配置操作工具
 */
public class ConfigUtils {

    /**
     * 根据类型获取配置
     *
     * @author fzr
     * @param type 类型
     * @return Map<String, String>
     */
    public static Map<String, String> get(String type) {
        Map<String, String> cache = ConfigCache.get(type);
        if (!cache.isEmpty()) {
            return cache;
        }

        SystemConfigMapper model = SpringUtils.getBean(SystemConfigMapper.class);
        List<SystemConfig> configs = model.selectList(
                new QueryWrapper<SystemConfig>()
                        .select("id", "type"+"", "name", "value")
                        .eq("type", type));

        Map<String, String> map = new LinkedHashMap<>();
        for (SystemConfig config : configs) {
            map.put(config.getName(), config.getValue());
        }

        ConfigCache.set();
        return map;
    }

    /**
     * 根据类型和名称获取配置
     *
     * @author fzr
     * @param type 类型
     * @param name 名称
     * @return String
     */
    public static String get(String type, String name) {
        String cache = ConfigCache.get(type, name);
        if (!StringUtils.isNull(cache) && !StringUtils.isEmpty(cache)) {
            return cache;
        }

        SystemConfigMapper model = SpringUtils.getBean(SystemConfigMapper.class);
        SystemConfig config = model.selectOne(
                new QueryWrapper<SystemConfig>()
                        .select("id", "type", "name", "value")
                        .eq("name", name)
                        .eq("type", type));

        ConfigCache.set();
        return config.getValue();
    }

    /**
     * 根据类型和名称获取配置
     *
     * @author fzr
     * @param type 类型
     * @param name 名称
     * @return String
     */
    public static String get(String type, String name, String defaults) {
        String cache = ConfigCache.get(type, name);
        if (!StringUtils.isNull(cache) && !StringUtils.isEmpty(cache)) {
            return cache;
        }

        SystemConfigMapper model = SpringUtils.getBean(SystemConfigMapper.class);
        SystemConfig config = model.selectOne(
                new QueryWrapper<SystemConfig>()
                        .select("id", "type", "name", "value")
                        .eq("type", type)
                        .eq("name", name));

        if (config == null) {
            return defaults;
        }

        ConfigCache.set();
        return config.getValue();
    }

    /**
     * 根据类型和名称获取配置(JSON自定转Map)
     *
     * @author fzr
     * @param type 类型
     * @param name 名称
     * @return String
     */
    public static Map<String, String> getMap(String type, String name) {
        String cache = ConfigCache.get(type, name);
        if (!StringUtils.isNull(cache) && !StringUtils.isEmpty(cache)) {
            return MapUtils.jsonToMap(cache);
        }

        SystemConfigMapper model = SpringUtils.getBean(SystemConfigMapper.class);

        SystemConfig config = model.selectOne(
                new QueryWrapper<SystemConfig>()
                        .select("id", "type", "name", "value")
                        .eq("type", type)
                        .eq("name", name));

        if (config == null) {
            return null;
        }

        if (config.getValue().equals("") || config.getValue().equals("[]") || config.getValue().equals("{}")) {
            return new LinkedHashMap<>();
        }

        ConfigCache.set();
        return MapUtils.jsonToMap(config.getValue());
    }

    /**
     * 设置配置的值
     *
     * @author fzr
     * @param type 类型
     * @param name 名称
     * @param val 值
     */
    public static void set(String type, String name, String val) {
        SystemConfigMapper model = SpringUtils.getBean(SystemConfigMapper.class);
        SystemConfig config = model.selectOne(
                new QueryWrapper<SystemConfig>()
                        .eq("type", type)
                        .eq("name", name));

        if (config != null) {
            config.setValue(val);
            config.setUpdateTime(System.currentTimeMillis() / 1000);
            model.updateById(config);
        } else {
            SystemConfig systemConfig = new SystemConfig();
            systemConfig.setType(type);
            systemConfig.setName(name);
            systemConfig.setValue(val);
            systemConfig.setCreateTime(System.currentTimeMillis() / 1000);
            systemConfig.setUpdateTime(System.currentTimeMillis() / 1000);
            model.insert(systemConfig);
        }

        ConfigCache.set();
    }

}

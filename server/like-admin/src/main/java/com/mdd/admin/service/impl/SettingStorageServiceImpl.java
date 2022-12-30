package com.mdd.admin.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.admin.service.ISettingStorageService;
import com.mdd.common.util.ConfigUtils;
import com.mdd.common.util.StringUtils;
import com.mdd.common.util.YmlUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 存储配置服务实现类
 */
@Service
public class SettingStorageServiceImpl implements ISettingStorageService {

    /**
     * 存储列表
     *
     * @author fzr
     * @return List<Map<String, Object>>
     */
    @Override
    public List<Map<String, Object>> list() {
        String engine = ConfigUtils.get("storage", "default", "local");
        List<Map<String, Object>> list = new LinkedList<>();

        Map<String, Object> local = new LinkedHashMap<>();
        local.put("name", "本地存储");
        local.put("alias", "local");
        local.put("describe", "存储在本地服务器");
        local.put("status", engine.equals("local") ? 1 : 0);
        list.add(local);

        Map<String, Object> qiniu = new LinkedHashMap<>();
        qiniu.put("name", "七牛云存储");
        qiniu.put("alias", "qiniu");
        qiniu.put("describe", "存储在七牛云，请前往七牛云开通存储服务");
        qiniu.put("status", engine.equals("qiniu") ? 1 : 0);
        list.add(qiniu);

        Map<String, Object> aliyun = new LinkedHashMap<>();
        aliyun.put("name", "阿里云OSS");
        aliyun.put("alias", "aliyun");
        aliyun.put("describe", "存储在阿里云，请前往阿里云开通存储服务");
        aliyun.put("status", engine.equals("aliyun") ? 1 : 0);
        list.add(aliyun);

        Map<String, Object> qcloud = new LinkedHashMap<>();
        qcloud.put("name", "腾讯云COS");
        qcloud.put("alias", "qcloud");
        qcloud.put("describe", "存储在腾讯云，请前往腾讯云开通存储服务");
        qcloud.put("status", engine.equals("qcloud") ? 1 : 0);
        list.add(qcloud);

        return list;
    }

    /**
     * 存储详情
     *
     * @author fzr
     * @param alias 存储别名
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> detail(String alias) {
        String env = YmlUtils.get("like.production");
        boolean envStatus = StringUtils.isNotNull(env) && env.equals("true");

        String engine = ConfigUtils.get("storage", "default", "local");
        Map<String, String> config = ConfigUtils.getMap("storage", alias);
        config = StringUtils.isNotNull(config) ? config : Collections.emptyMap();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", config.getOrDefault("name", ""));
        map.put("alias", alias);
        map.put("status", engine.equals(alias) ? 1 : 0);
        if (!alias.equals("local")) {
            map.put("bucket", config.getOrDefault("bucket", ""));
            map.put("secretKey", envStatus ? "******" : config.getOrDefault("secretKey", ""));
            map.put("accessKey", envStatus ? "******" : config.getOrDefault("accessKey", ""));
            map.put("domain", config.getOrDefault("domain", ""));
            if (alias.equals("qcloud")) {
                map.put("region", config.getOrDefault("region", ""));
            }
        }

        return map;
    }

    /**
     * 存储编辑
     *
     * @author fzr
     * @param params 参数
     */
    @Override
    public void edit(Map<String, String> params) {
        Assert.notNull(params.get("alias"), "alias参数缺失");
        Assert.notNull(params.get("status"), "status参数缺失");
        Map<String, String> map = new LinkedHashMap<>();

        map.put("name", "本地存储");
        if (!params.get("alias").equals("local")) {
            map.put("bucket", params.getOrDefault("bucket", ""));
            map.put("secretKey", params.getOrDefault("secretKey", ""));
            map.put("accessKey", params.getOrDefault("accessKey", ""));
            map.put("domain", params.getOrDefault("domain", ""));
            switch (params.get("alias")) {
                case "qcloud":
                    map.put("name", "腾讯云存储");
                    map.put("region", params.getOrDefault("region", ""));
                    break;
                case "qiniu":
                    map.put("name", "七牛云存储");
                    break;
                case "aliyun":
                    map.put("name", "阿里云存储");
                    break;
            }
        }

        ConfigUtils.set("storage", params.get("alias"), JSON.toJSONString(map));

        String engine = ConfigUtils.get("storage", "default", "local");
        if (Integer.parseInt(params.get("status")) == 1) {
            ConfigUtils.set("storage", "default", params.get("alias"));
        } else if (engine.equals(params.get("alias")) && Integer.parseInt(params.get("status")) == 0) {
            ConfigUtils.set("storage", "default", "");
        }
    }

    /**
     * 引擎切换
     *
     * @author fzr
     * @param alias 引擎别名
     * @param status 状态
     */
    @Override
    public void change(String alias, Integer status) {
        String engine = ConfigUtils.get("storage", "default", "local");
        if (engine.equals(alias) && status == 0) {
            ConfigUtils.set("storage", "default", "");
        } else {
            ConfigUtils.set("storage", "default", alias);
        }
    }

}

package com.mdd.admin.service.setting.impl;

import com.alibaba.fastjson.JSON;
import com.mdd.admin.service.setting.ISettingSmsService;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 短信配置服务实现类
 */
@Service
public class SettingSmsServiceImpl implements ISettingSmsService {

    /**
     * 短信引擎列表
     *
     * @author fzr
     * @return List<Map<String, Object>>
     */
    @Override
    public List<Map<String, Object>> list() {
        String engine = ConfigUtil.get("sms", "default", "aliyun");
        List<Map<String, Object>> list = new LinkedList<>();

        Map<String, Object> aliyun = new LinkedHashMap<>();
        aliyun.put("name", "阿里云短信");
        aliyun.put("alias", "aliyun");
        aliyun.put("status", engine.equals("aliyun") ? 1 : 0);
        list.add(aliyun);

        Map<String, Object> tencent = new LinkedHashMap<>();
        tencent.put("name", "腾讯云短信");
        tencent.put("alias", "tencent");
        tencent.put("status", engine.equals("tencent") ? 1 : 0);
        list.add(tencent);

        Map<String, Object> huawei = new LinkedHashMap<>();
        huawei.put("name", "华为云短信");
        huawei.put("alias", "huawei");
        huawei.put("status", engine.equals("huawei") ? 1 : 0);
        list.add(huawei);

        return list;
    }

    /**
     * 短信引擎详情
     *
     * @author fzr
     * @param alias 别名
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> detail(String alias) {
        String engine = ConfigUtil.get("sms", "default", "local");
        Map<String, String> config = ConfigUtil.getMap("sms", alias);
        config = StringUtil.isNotNull(config) ? config : Collections.emptyMap();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", config.getOrDefault("name", ""));
        map.put("status", engine.equals(alias) ? 1 : 0);
        map.put("alias", alias);
        map.put("sign", config.getOrDefault("sign", ""));

        switch (alias) {
            case "aliyun":
                map.put("appKey", config.getOrDefault("appKey", ""));
                map.put("secretKey", config.getOrDefault("secretKey", ""));
                break;
            case "tencent":
                map.put("appId", config.getOrDefault("appId", ""));
                map.put("secretId", config.getOrDefault("secretId", ""));
                map.put("secretKey", config.getOrDefault("secretKey", ""));
                break;
            case "huawei":
                break;
        }

        return map;
    }

    /**
     * 短信引擎保存
     *
     * @author fzr
     * @param params 参数
     */
    @Override
    public void save(Map<String, String> params) {
        Map<String, String> map = new LinkedHashMap<>();

        switch (params.get("alias")) {
            case "aliyun":
                map.put("name", "阿里云短信");
                map.put("alias", "aliyun");
                map.put("sign", params.getOrDefault("sign", ""));
                map.put("appKey", params.getOrDefault("appKey", ""));
                map.put("secretKey", params.getOrDefault("secretKey", ""));
                break;
            case "tencent":
                System.out.println("来了吗");
                map.put("name", "腾讯云短信");
                map.put("alias", "aliyun");
                map.put("sign", params.getOrDefault("sign", ""));
                map.put("appId", params.getOrDefault("appId", ""));
                map.put("secretId", params.getOrDefault("secretId", ""));
                map.put("secretKey", params.getOrDefault("secretKey", ""));
                break;
        }

        ConfigUtil.set("sms", params.get("alias"), JSON.toJSONString(map));

        String engine = ConfigUtil.get("sms", "default", "");
        if (Integer.parseInt(params.get("status")) == 1) {
            ConfigUtil.set("sms", "default", params.get("alias"));
        } else if (engine.equals(params.get("alias")) && Integer.parseInt(params.get("status")) == 0) {
            ConfigUtil.set("sms", "default", "");
        }
    }

}

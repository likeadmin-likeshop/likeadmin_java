package com.mdd.admin.service.channel.impl;

import com.mdd.admin.service.channel.IChannelWxService;
import com.mdd.common.utils.ConfigUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 微信开放平台设置服务实现类
 */
@Service
public class ChannelWxServiceImpl implements IChannelWxService {

    /**
     * 微信开放平台设置详情
     *
     * @author fzr
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> detail() {
        Map<String, String> config = ConfigUtil.get("wx_channel");
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("appId", config.getOrDefault("appId", "0"));
        map.put("appSecret", config.getOrDefault("appSecret", "0"));
        return map;
    }

    /**
     * 微信开放平台设置保存
     *
     * @author fzr
     * @param param 参数
     */
    @Override
    public void save(Map<String, String> param) {
        ConfigUtil.set("wx_channel", "appId", param.getOrDefault("appId", ""));
        ConfigUtil.set("wx_channel", "appSecret", param.getOrDefault("appSecret", ""));
    }

}

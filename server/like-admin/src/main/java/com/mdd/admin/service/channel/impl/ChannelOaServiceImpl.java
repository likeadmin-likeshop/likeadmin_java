package com.mdd.admin.service.channel.impl;

import com.mdd.admin.service.channel.IChannelOaService;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.UrlUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 公众号渠道设置服务实现类
 */
@Service
public class ChannelOaServiceImpl implements IChannelOaService {

    /**
     * 公众号渠道设置详情
     *
     * @author fzr
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> detail() {
        Map<String, String> config = ConfigUtil.get("oa_channel");
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", config.getOrDefault("name", ""));
        map.put("primaryId", config.getOrDefault("primaryId", ""));
        map.put("qrCode", UrlUtil.toAbsoluteUrl(config.getOrDefault("qrCode", "")));
        map.put("appId", config.getOrDefault("appId", ""));
        map.put("appSecret", config.getOrDefault("appSecret", ""));
        return map;
    }

    /**
     * 公众号渠道设置保存
     *
     * @author fzr
     * @param param 参数
     */
    @Override
    public void save(Map<String, String> param) {
        ConfigUtil.set("mp_channel", "name", param.getOrDefault("name", ""));
        ConfigUtil.set("mp_channel", "primaryId", param.getOrDefault("primaryId", ""));
        ConfigUtil.set("mp_channel", "qrCode", UrlUtil.toRelativeUrl(param.getOrDefault("qrCode", "")));
        ConfigUtil.set("mp_channel", "appId", param.getOrDefault("appId", ""));
        ConfigUtil.set("mp_channel", "appSecret", param.getOrDefault("appSecret", ""));
    }

}

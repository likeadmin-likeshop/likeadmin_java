package com.mdd.admin.service.channel.impl;

import com.mdd.admin.service.channel.IChannelH5Service;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.RequestUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * H5渠道设置服务实现类
 */
@Service
public class ChannelH5ServiceImpl implements IChannelH5Service {

    /**
     * H5渠道配置详情
     *
     * @author fzr
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> detail() {
        Map<String, String> config = ConfigUtil.get("h5_channel");
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", Integer.parseInt(config.getOrDefault("status", "0")));
        map.put("close", Integer.parseInt(config.getOrDefault("close", "0")));
        map.put("url", config.getOrDefault("url", ""));
        map.put("accessLink", RequestUtil.domain()+"/mobile");
        return map;
    }

    /**
     * H5渠道配置保存
     *
     * @author fzr
     * @param param 参数
     */
    @Override
    public void save(Map<String, String> param) {
        ConfigUtil.set("h5_channel", "status", param.getOrDefault("status", "0"));
        ConfigUtil.set("h5_channel", "close", param.getOrDefault("close", "0"));
        ConfigUtil.set("h5_channel", "url", param.getOrDefault("url", "0"));
    }

}

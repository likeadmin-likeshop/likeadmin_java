package com.mdd.admin.service.channel.impl;

import com.mdd.admin.service.channel.IChannelH5Service;
import com.mdd.admin.validate.channel.ChannelH5Param;
import com.mdd.admin.vo.channel.ChannelH5Vo;
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
     * @return ChannelH5Vo
     */
    @Override
    public ChannelH5Vo detail() {
        Map<String, String> config = ConfigUtil.get("h5_channel");
        ChannelH5Vo vo = new ChannelH5Vo();
        vo.setStatus(Integer.parseInt(config.getOrDefault("status", "0")));
        vo.setClose(Integer.parseInt(config.getOrDefault("close", "0")));
        vo.setUrl(config.getOrDefault("url", ""));
        return vo;
    }

    /**
     * H5渠道配置保存
     *
     * @author fzr
     * @param channelH5Param 参数
     */
    @Override
    public void save(ChannelH5Param channelH5Param) {
        ConfigUtil.set("h5_channel", "status", String.valueOf(channelH5Param.getStatus()));
        ConfigUtil.set("h5_channel", "close", String.valueOf(channelH5Param.getClose()));
        ConfigUtil.set("h5_channel", "url", channelH5Param.getUrl());
    }

}

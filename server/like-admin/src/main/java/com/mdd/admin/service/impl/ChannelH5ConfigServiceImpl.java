package com.mdd.admin.service.impl;

import com.mdd.admin.service.IChannelH5ConfigService;
import com.mdd.admin.validate.channel.ChannelH5Validate;
import com.mdd.admin.vo.channel.ChannelH5Vo;
import com.mdd.common.util.ConfigUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * H5渠道设置服务实现类
 */
@Service
public class ChannelH5ConfigServiceImpl implements IChannelH5ConfigService {

    /**
     * H5渠道配置详情
     *
     * @author fzr
     * @return ChannelH5Vo
     */
    @Override
    public ChannelH5Vo detail() {
        Map<String, String> config = ConfigUtils.get("h5_channel");
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
     * @param channelH5Validate 参数
     */
    @Override
    public void save(ChannelH5Validate channelH5Validate) {
        ConfigUtils.set("h5_channel", "status", String.valueOf(channelH5Validate.getStatus()));
        ConfigUtils.set("h5_channel", "close", String.valueOf(channelH5Validate.getClose()));
        ConfigUtils.set("h5_channel", "url", channelH5Validate.getUrl());
    }

}

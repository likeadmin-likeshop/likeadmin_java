package com.mdd.admin.service.channel.impl;

import com.mdd.admin.service.channel.IChannelWxService;
import com.mdd.admin.validate.channel.ChannelWxParam;
import com.mdd.admin.vo.channel.ChannelWxVo;
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
     * @return ChannelWxVo
     */
    @Override
    public ChannelWxVo detail() {
        Map<String, String> config = ConfigUtil.get("wx_channel");
        ChannelWxVo vo = new ChannelWxVo();

        vo.setAppId(config.getOrDefault("appId", ""));
        vo.setAppSecret(config.getOrDefault("appSecret", ""));
        return vo;
    }

    /**
     * 微信开放平台设置保存
     *
     * @author fzr
     * @param channelWxParam 参数
     */
    @Override
    public void save(ChannelWxParam channelWxParam) {
        ConfigUtil.set("wx_channel", "appId", channelWxParam.getAppId());
        ConfigUtil.set("wx_channel", "appSecret", channelWxParam.getAppSecret());
    }

}

package com.mdd.admin.service.impl;

import com.mdd.admin.service.IChannelMpConfigService;
import com.mdd.admin.validate.channel.ChannelMpValidate;
import com.mdd.admin.vo.channel.ChannelMpVo;
import com.mdd.common.util.*;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 微信小程序渠道服务实现类
 */
@Service
public class ChannelMpConfigServiceImpl implements IChannelMpConfigService {

    /**
     * 微信小程序渠道详情
     *
     * @author fzr
     * @return ChannelMpVo
     */
    @Override
    public ChannelMpVo detail() {
        Map<String, String> config = ConfigUtils.get("mp_channel");

        String env = YmlUtils.get("like.production");
        boolean envStatus = StringUtils.isNotNull(env) && env.equals("true");

        ChannelMpVo vo = new ChannelMpVo();
        vo.setName(config.getOrDefault("name", ""));
        vo.setPrimaryId(config.getOrDefault("primaryId", ""));
        vo.setAppId(envStatus ? "******" : config.getOrDefault("appId", ""));
        vo.setAppSecret(envStatus ? "******" : config.getOrDefault("appSecret", ""));
        vo.setQrCode(UrlUtils.toAbsoluteUrl(config.getOrDefault("qrCode", "")));

        String domain = RequestUtils.domain();
        vo.setRequestDomain(domain);
        vo.setSocketDomain(domain);
        vo.setUploadFileDomain(domain);
        vo.setDownloadFileDomain(domain);
        vo.setUdpDomain(domain);
        vo.setTcpDomain(domain);
        vo.setBusinessDomain(domain);

        return vo;
    }

    /**
     * 微信小程序渠道保存
     *
     * @author fzr
     * @param channelMpValidate 参数
     */
    @Override
    public void save(ChannelMpValidate channelMpValidate) {
        ConfigUtils.set("mp_channel", "name", channelMpValidate.getName());
        ConfigUtils.set("mp_channel", "primaryId", channelMpValidate.getPrimaryId());
        ConfigUtils.set("mp_channel", "appId", channelMpValidate.getAppId());
        ConfigUtils.set("mp_channel", "appSecret", channelMpValidate.getAppSecret());
        ConfigUtils.set("mp_channel", "qrCode", UrlUtils.toRelativeUrl(channelMpValidate.getQrCode()));
    }

}

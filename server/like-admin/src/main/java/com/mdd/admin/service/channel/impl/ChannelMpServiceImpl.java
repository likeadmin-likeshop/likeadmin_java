package com.mdd.admin.service.channel.impl;

import com.mdd.admin.service.channel.IChannelMpService;
import com.mdd.admin.validate.channel.ChannelMpParam;
import com.mdd.admin.vo.channel.ChannelMpVo;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.RequestUtil;
import com.mdd.common.utils.UrlUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 微信小程序渠道服务实现类
 */
@Service
public class ChannelMpServiceImpl implements IChannelMpService {

    /**
     * 微信小程序渠道详情
     *
     * @author fzr
     * @return ChannelMpVo
     */
    @Override
    public ChannelMpVo detail() {
        Map<String, String> config = ConfigUtil.get("mp_channel");

        ChannelMpVo vo = new ChannelMpVo();
        vo.setName(config.getOrDefault("name", ""));
        vo.setPrimaryId(config.getOrDefault("primaryId", ""));
        vo.setAppId(config.getOrDefault("appId", ""));
        vo.setAppSecret(config.getOrDefault("appSecret", ""));
        vo.setQrCode(UrlUtil.toAbsoluteUrl(config.getOrDefault("qrCode", "")));


        String domain = RequestUtil.domain();
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
     * @param channelMpParam 参数
     */
    @Override
    public void save(ChannelMpParam channelMpParam) {
        ConfigUtil.set("mp_channel", "name", channelMpParam.getName());
        ConfigUtil.set("mp_channel", "primaryId", channelMpParam.getPrimaryId());
        ConfigUtil.set("mp_channel", "appId", channelMpParam.getAppId());
        ConfigUtil.set("mp_channel", "appSecret", channelMpParam.getAppSecret());
        ConfigUtil.set("mp_channel", "qrCode", UrlUtil.toRelativeUrl(channelMpParam.getQrCode()));
    }

}

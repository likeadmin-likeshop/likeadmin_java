package com.mdd.admin.service.impl;

import com.mdd.admin.service.IChannelMpConfigService;
import com.mdd.admin.validate.channel.ChannelMpValidate;
import com.mdd.admin.vo.channel.ChannelMpVo;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.RequestUtil;
import com.mdd.common.utils.UrlUtil;
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
     * @param channelMpValidate 参数
     */
    @Override
    public void save(ChannelMpValidate channelMpValidate) {
        ConfigUtil.set("mp_channel", "name", channelMpValidate.getName());
        ConfigUtil.set("mp_channel", "primaryId", channelMpValidate.getPrimaryId());
        ConfigUtil.set("mp_channel", "appId", channelMpValidate.getAppId());
        ConfigUtil.set("mp_channel", "appSecret", channelMpValidate.getAppSecret());
        ConfigUtil.set("mp_channel", "qrCode", UrlUtil.toRelativeUrl(channelMpValidate.getQrCode()));
    }

}

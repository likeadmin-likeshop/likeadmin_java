package com.mdd.admin.service.channel.impl;

import com.mdd.admin.service.channel.IChannelOaService;
import com.mdd.admin.validate.channel.ChannelOaParam;
import com.mdd.admin.vo.channel.ChannelOaVo;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.RequestUtil;
import com.mdd.common.utils.UrlUtil;
import org.springframework.stereotype.Service;

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
     * @return ChannelOaVo
     */
    @Override
    public ChannelOaVo detail() {
        Map<String, String> config = ConfigUtil.get("oa_channel");
        ChannelOaVo vo = new ChannelOaVo();

        vo.setQrCode(UrlUtil.toAbsoluteUrl(config.getOrDefault("qrCode", "")));
        vo.setName(config.getOrDefault("name", ""));
        vo.setPrimaryId(config.getOrDefault("primaryId", ""));
        vo.setAppId(config.getOrDefault("appId", ""));
        vo.setAppSecret(config.getOrDefault("appSecret", ""));
        vo.setUrl(config.getOrDefault("url", ""));
        vo.setToken(config.getOrDefault("token", ""));
        vo.setEncodingAesKey(config.getOrDefault("encodingAesKey", ""));
        vo.setEncryptionType(Integer.parseInt(config.getOrDefault("encryptionType", "1")));

        String domain = RequestUtil.domain();
        vo.setBusinessDomain(domain);
        vo.setJsDomain(domain);
        vo.setWebDomain(domain);

        return vo;
    }

    /**
     * 公众号渠道设置保存
     *
     * @author fzr
     * @param channelOaParam 参数
     */
    @Override
    public void save(ChannelOaParam channelOaParam) {
        ConfigUtil.set("oa_channel", "name", channelOaParam.getName());
        ConfigUtil.set("oa_channel", "primaryId", channelOaParam.getPrimaryId());
        ConfigUtil.set("oa_channel", "qrCode", UrlUtil.toRelativeUrl(channelOaParam.getQrCode()));
        ConfigUtil.set("oa_channel", "appId", channelOaParam.getAppId());
        ConfigUtil.set("oa_channel", "appSecret", channelOaParam.getAppSecret());
        ConfigUtil.set("oa_channel", "url", channelOaParam.getUrl());
        ConfigUtil.set("oa_channel", "token", channelOaParam.getToken());
        ConfigUtil.set("oa_channel", "encodingAesKey", channelOaParam.getEncodingAesKey());
        ConfigUtil.set("oa_channel", "encryptionType", String.valueOf(channelOaParam.getEncryptionType()));
    }

}

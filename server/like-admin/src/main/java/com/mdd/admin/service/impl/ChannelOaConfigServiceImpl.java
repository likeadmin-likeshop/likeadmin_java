package com.mdd.admin.service.impl;

import com.mdd.admin.service.IChannelOaConfigService;
import com.mdd.admin.validate.channel.ChannelOaValidate;
import com.mdd.admin.vo.channel.ChannelOaVo;
import com.mdd.common.util.*;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 公众号渠道设置服务实现类
 */
@Service
public class ChannelOaConfigServiceImpl implements IChannelOaConfigService {

    /**
     * 公众号渠道设置详情
     *
     * @author fzr
     * @return ChannelOaVo
     */
    @Override
    public ChannelOaVo detail() {
        Map<String, String> config = ConfigUtils.get("oa_channel");
        ChannelOaVo vo = new ChannelOaVo();

        String env = YmlUtils.get("like.production");
        boolean envStatus = StringUtils.isNotNull(env) && env.equals("true");

        vo.setQrCode(UrlUtils.toAbsoluteUrl(config.getOrDefault("qrCode", "")));
        vo.setName(config.getOrDefault("name", ""));
        vo.setPrimaryId(config.getOrDefault("primaryId", ""));
        vo.setAppId(envStatus ? "******" : config.getOrDefault("appId", ""));
        vo.setAppSecret(envStatus ? "******" : config.getOrDefault("appSecret", ""));
        vo.setUrl(config.getOrDefault("url", ""));
        vo.setToken(config.getOrDefault("token", ""));
        vo.setEncodingAesKey(config.getOrDefault("encodingAesKey", ""));
        vo.setEncryptionType(Integer.parseInt(config.getOrDefault("encryptionType", "1")));

        String domain = RequestUtils.domain();
        vo.setBusinessDomain(domain);
        vo.setJsDomain(domain);
        vo.setWebDomain(domain);

        return vo;
    }

    /**
     * 公众号渠道设置保存
     *
     * @author fzr
     * @param channelOaValidate 参数
     */
    @Override
    public void save(ChannelOaValidate channelOaValidate) {
        ConfigUtils.set("oa_channel", "name", channelOaValidate.getName());
        ConfigUtils.set("oa_channel", "primaryId", channelOaValidate.getPrimaryId());
        ConfigUtils.set("oa_channel", "qrCode", UrlUtils.toRelativeUrl(channelOaValidate.getQrCode()));
        ConfigUtils.set("oa_channel", "appId", channelOaValidate.getAppId());
        ConfigUtils.set("oa_channel", "appSecret", channelOaValidate.getAppSecret());
        ConfigUtils.set("oa_channel", "url", channelOaValidate.getUrl());
        ConfigUtils.set("oa_channel", "token", channelOaValidate.getToken());
        ConfigUtils.set("oa_channel", "encodingAesKey", channelOaValidate.getEncodingAesKey());
        ConfigUtils.set("oa_channel", "encryptionType", String.valueOf(channelOaValidate.getEncryptionType()));
    }

}

package com.mdd.common.utils;

import com.qcloud.cos.model.ciModel.auditing.Conf;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;

import java.util.Map;

public class WeChatUtil {

    /**
     * 微信公众号
     *
     * @author fzr
     * @return WxMpService
     */
    public static WxMpService official() {
        Map<String, String> config = ConfigUtil.get("oa_channel");

        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId(config.getOrDefault("appId", "").trim());
        wxMpDefaultConfig.setSecret(config.getOrDefault("appSecret", "").trim());
        wxMpDefaultConfig.setToken(config.getOrDefault("token", "").trim());
        wxMpDefaultConfig.setAesKey(config.getOrDefault("encodingAesKey", "").trim());

        WxMpService service = new WxMpServiceImpl();
        service.setWxMpConfigStorage(wxMpDefaultConfig);
        return service;
    }

}

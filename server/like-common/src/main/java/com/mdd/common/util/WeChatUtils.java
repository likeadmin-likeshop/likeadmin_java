package com.mdd.common.util;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;

import java.util.Map;

public class WeChatUtils {

    /**
     * 微信小程序
     *
     * @author fzr
     * @return WxMaService
     */
    public static WxMaService mnp() {
        Map<String, String> config = ConfigUtils.get("mp_channel");

        WxMaService service = new WxMaServiceImpl();
        WxMaDefaultConfigImpl wxConfig = new WxMaDefaultConfigImpl();
        wxConfig.setAppid(config.getOrDefault("appId", ""));
        wxConfig.setSecret(config.getOrDefault("appSecret", ""));
        service.setWxMaConfig(wxConfig);
        return service;
    }


    /**
     * 微信公众号
     *
     * @author fzr
     * @return WxMpService
     */
    public static WxMpService official() {
        Map<String, String> config = ConfigUtils.get("oa_channel");

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

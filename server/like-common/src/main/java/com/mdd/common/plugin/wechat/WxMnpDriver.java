package com.mdd.common.plugin.wechat;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.mdd.common.util.ConfigUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信基础驱动
 */
@Component
public class WxMnpDriver {

    private static WxMaService wxMaService;

    private static WxMpService wxMpService;

    /**
     * 微信小程序依赖注入
     */
    @Resource
    public void setWxMaService(WxMaService wxMaService) {
        WxMnpDriver.wxMaService = wxMaService;
    }

    /**
     * 微信公众号依赖注入
     */
    @Resource
    public void setWxOaService(WxMpService wxMpService) {
        WxMnpDriver.wxMpService = wxMpService;
    }


    /**
     * 微信小程序
     *
     * @author fzr
     * @return WxMaService
     */
    public static WxMaService mnp() {
        Map<String, String> config = ConfigUtils.get("mp_channel");

        WxMaDefaultConfigImpl wxConfig = new WxMaDefaultConfigImpl();
        wxConfig.setAppid(config.getOrDefault("appId", ""));
        wxConfig.setSecret(config.getOrDefault("appSecret", ""));
        wxMaService.setWxMaConfig(wxConfig);

        return wxMaService;
    }

    /**
     * 微信公众号
     *
     * @author fzr
     * @return WxMpService
     */
    public static WxMpService oa() {
        Map<String, String> config = ConfigUtils.get("oa_channel");

        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId(config.getOrDefault("appId", "").trim());
        wxMpDefaultConfig.setSecret(config.getOrDefault("appSecret", "").trim());
        wxMpDefaultConfig.setToken(config.getOrDefault("token", "").trim());
        wxMpDefaultConfig.setAesKey(config.getOrDefault("encodingAesKey", "").trim());
        wxMpService.setWxMpConfigStorage(wxMpDefaultConfig);

        return wxMpService;
    }
}

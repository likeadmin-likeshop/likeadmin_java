package com.mdd.common.config.wechat;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.service.WxPayService;
import com.mdd.common.entity.system.SystemConfig;
import com.mdd.common.mapper.system.SystemConfigMapper;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author panweiliang
 */
@Configuration
@ConditionalOnClass(WxPayService.class)
@AllArgsConstructor
public class WxMnpConfiguration {

    @Resource
    private final SystemConfigMapper systemConfigMapper;

    /**
     * 微信小程序配置
     *
     * @author fzr
     * @return WxMaService
     */
    @Bean
    @ConditionalOnMissingBean
    public WxMaService wxMnpService() {
        Map<String, String> config = this.getChannelConfig("mp_channel");

        WxMaDefaultConfigImpl wxConfig = new WxMaDefaultConfigImpl();
        wxConfig.setAppid(config.getOrDefault("appId", ""));
        wxConfig.setSecret(config.getOrDefault("appSecret", ""));

        WxMaService wxService = new WxMaServiceImpl();
        wxService.setWxMaConfig(wxConfig);
        return wxService;
    }

    /**
     * 微信公众号配置
     *
     * @author zr
     * @return WxMpService
     */
    @Bean
    @ConditionalOnMissingBean
    public WxMpService wxOaService() {
        Map<String, String> config = this.getChannelConfig("oa_channel");

        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setAppId(config.getOrDefault("appId", "").trim());
        wxMpDefaultConfig.setSecret(config.getOrDefault("appSecret", "").trim());
        wxMpDefaultConfig.setToken(config.getOrDefault("token", "").trim());
        wxMpDefaultConfig.setAesKey(config.getOrDefault("encodingAesKey", "").trim());

        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(wxMpDefaultConfig);
        return wxService;
    }

    /**
     * 配置读取
     *
     * @author fzr
     * @param type 类型
     * @return Map<String, String>
     */
    private Map<String, String> getChannelConfig(String type) {
        List<SystemConfig> configs = systemConfigMapper.selectList(
                    new QueryWrapper<SystemConfig>()
                        .select("id", "type", "name", "value")
                        .eq("type", type));

        Map<String, String> map = new LinkedHashMap<>();
        for (SystemConfig config : configs) {
            map.put(config.getName(), config.getValue());
        }

        return map;
    }

}

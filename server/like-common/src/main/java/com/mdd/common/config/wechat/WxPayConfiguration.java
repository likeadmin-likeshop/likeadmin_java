package com.mdd.common.config.wechat;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.mdd.common.entity.setting.DevPayConfig;
import com.mdd.common.mapper.setting.DevPayConfigMapper;
import com.mdd.common.util.ConfigUtils;
import com.mdd.common.util.MapUtils;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;

@Configuration
@ConditionalOnClass(WxPayService.class)
@AllArgsConstructor
public class WxPayConfiguration {

    @Resource
    DevPayConfigMapper devPayConfigMapper;

    /**
     * 微信小程序支付配置
     *
     * @author fzr
     * @return WxPayService
     */
    @Bean
    @ConditionalOnMissingBean
    public WxPayService mnpPayService() {
        DevPayConfig config = devPayConfigMapper.selectOne(
                new QueryWrapper<DevPayConfig>()
                    .eq("way", 2)
                    .last("limit 1"));

        Map<String, String> params = MapUtils.jsonToMap(config.getParams().toString());
        String appId = ConfigUtils.get("mp_channel", "appId", "");
        String mchId = params.get("mch_id");
        String paySignKey  = params.get("pay_sign_key");
        byte[] privateKey  = params.getOrDefault("private_key", "").getBytes();
        byte[] privateCert = params.getOrDefault("private_cert", "").getBytes();

        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(appId);
        payConfig.setMchId(mchId);
        payConfig.setApiV3Key(paySignKey);
        payConfig.setPrivateKeyContent(privateKey);
        payConfig.setPrivateCertContent(privateCert);
        payConfig.setUseSandboxEnv(false);
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }

    /**
     * 微信公众号支付配置
     *
     * @author fzr
     * @return WxPayService
     */
    @Bean
    @ConditionalOnMissingBean
    public WxPayService wxOaService() {
        DevPayConfig config = devPayConfigMapper.selectOne(
                new QueryWrapper<DevPayConfig>()
                        .eq("way", 2)
                        .last("limit 1"));

        Map<String, String> params = MapUtils.jsonToMap(config.getParams().toString());
        String appId = ConfigUtils.get("oa_channel", "appId", "");
        String mchId = params.get("mch_id");
        String paySignKey  = params.get("pay_sign_key");
        byte[] privateKey  = params.getOrDefault("private_key", "").getBytes();
        byte[] privateCert = params.getOrDefault("private_cert", "").getBytes();

        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setMchId(mchId);
        payConfig.setAppId(appId);
        payConfig.setApiV3Key(paySignKey);
        payConfig.setPrivateKeyContent(privateKey);
        payConfig.setPrivateCertContent(privateCert);
        payConfig.setUseSandboxEnv(false);
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }

}

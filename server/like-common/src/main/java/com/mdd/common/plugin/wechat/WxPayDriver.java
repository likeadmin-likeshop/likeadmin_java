package com.mdd.common.plugin.wechat;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.mdd.common.entity.setting.DevPayConfig;
import com.mdd.common.enums.ClientEnum;
import com.mdd.common.mapper.setting.DevPayConfigMapper;
import com.mdd.common.util.ConfigUtils;
import com.mdd.common.util.MapUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信支付驱动
 */
@Component
public class WxPayDriver {

    private static DevPayConfigMapper devPayConfigMapper;

    private static WxPayService wxPayService;

    /**
     * 注入支付配置依赖
     */
    @Resource
    public void setDevPayConfigMapper(DevPayConfigMapper devPayConfigMapper) {
        WxPayDriver.devPayConfigMapper = devPayConfigMapper;
    }

    /**
     * 注入微信支付依赖
     */
    @Resource
    public void setWxPayService(WxPayService wxPayService) {
        WxPayDriver.wxPayService = wxPayService;
    }

    /**
     * 对象句柄
     *
     * @author fzr
     * @return WxPayService
     */
    public static WxPayService handler(Integer terminal) {
        if (ClientEnum.OA.getCode() == terminal) {
            resetConfig("oa");
        } else {
            resetConfig("mnp");
        }
        return wxPayService;
    }

    /**
     * 重设配置
     *
     * @author fzr
     * @param type 类型
     */
    private static void resetConfig(String type) {
        DevPayConfig config = devPayConfigMapper.selectOne(
                new QueryWrapper<DevPayConfig>()
                        .eq("way", 2)
                        .last("limit 1"));

        String scene = type.equals("oa") ? "oa_channel" : "mp_channel";
        String appId = ConfigUtils.get(scene, "appId", "");

        Map<String, String> params = MapUtils.jsonToMap(config.getParams().toString());
        String mchId = params.get("mch_id");
        String paySignKey  = params.get("pay_sign_key");
        byte[] privateCert = params.getOrDefault("private_cert", "").getBytes();
        byte[] privateKey  = params.getOrDefault("private_key", "").getBytes();

        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(appId);
        payConfig.setMchId(mchId);
        payConfig.setApiV3Key(paySignKey);
        payConfig.setPrivateKeyContent(privateKey);
        payConfig.setPrivateCertContent(privateCert);
        payConfig.setUseSandboxEnv(false);
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
    }

}

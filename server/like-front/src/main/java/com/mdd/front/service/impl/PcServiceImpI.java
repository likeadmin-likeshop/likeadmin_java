package com.mdd.front.service.impl;

import com.mdd.common.config.GlobalConfig;
import com.mdd.common.util.ArrayUtils;
import com.mdd.common.util.ConfigUtils;
import com.mdd.common.util.UrlUtils;
import com.mdd.front.service.IPcService;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class PcServiceImpI implements IPcService {


    @Override
    public Map<String, Object> getConfig() {
        Map<String, Object> config = new LinkedHashMap<>();
        // 登录配置
        Map<String, Object> loginMap = new LinkedHashMap<>();
        Map<String, String> loginConfig = ConfigUtils.get("login");
        loginMap.put("loginWay", ArrayUtils.stringToListAsInt(loginConfig.getOrDefault("loginWay", ""), ","));
        loginMap.put("forceBindMobile", Integer.parseInt(loginConfig.getOrDefault("forceBindMobile", "0")));
        loginMap.put("openAgreement", Integer.parseInt(loginConfig.getOrDefault("openAgreement", "0")));
        loginMap.put("openOtherAuth", Integer.parseInt(loginConfig.getOrDefault("openOtherAuth", "0")));
        loginMap.put("autoLoginAuth", ArrayUtils.stringToListAsInt(loginConfig.getOrDefault("autoLoginAuth", ""), ","));

        // 网址信息
        Map<String, Object> websiteMap = new LinkedHashMap<>();
        Map<String, String> websiteConfig = ConfigUtils.get("website");
        String copyright = websiteConfig.getOrDefault("copyright", "[]");
        Map<String, String> copyrightMap = ArrayUtils.stringToListAsMapStr(copyright).get(0);

        websiteMap.put("shopName", websiteConfig.getOrDefault("shopName", "LikeAdmin"));
        websiteMap.put("shopLogo", UrlUtils.toAbsoluteUrl(websiteConfig.getOrDefault("shopLogo", "")));
        websiteMap.put("pcDesc", websiteConfig.getOrDefault("pcDesc", ""));
        websiteMap.put("pcIco", UrlUtils.toAbsoluteUrl(websiteConfig.getOrDefault("pcIco", "")));
        websiteMap.put("pcKeywords", websiteConfig.getOrDefault("pcKeywords", ""));
        websiteMap.put("pcLogo", UrlUtils.toAbsoluteUrl(websiteConfig.getOrDefault("pcLogo", "")));
        websiteMap.put("pcTitle", websiteConfig.getOrDefault("pcTitle", ""));

        //演示公众号和小程序二维码
        Map<String,String> qrCodeMap = new LinkedHashMap<>();
        qrCodeMap.put("mnp",UrlUtils.toAbsoluteUrl(ConfigUtils.get("mp_channel","qrCode")));
        qrCodeMap.put("oa",UrlUtils.toAbsoluteUrl(ConfigUtils.get("oa_channel","qrCode")));

        // 返回数据
        config.put("version", GlobalConfig.version);
        config.put("domain", UrlUtils.domain());
        config.put("login", loginMap);
        config.put("website", websiteMap);
        config.put("copyright",copyrightMap);
        config.put("qrcode",qrCodeMap);
        return config;
    }
}

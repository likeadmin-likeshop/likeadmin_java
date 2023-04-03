package com.mdd.front.service.impl;

import com.mdd.common.plugin.wechat.WxMnpDriver;
import com.mdd.common.util.ConfigUtils;
import com.mdd.front.service.IWechatService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WechatServiceImpl implements IWechatService {

    @Override
    public Map<String, Object> jsConfig(String url) throws Exception {
        String appId = ConfigUtils.get("oa_channel", "appId");
        String accessToken = WxMnpDriver.mnp().getAccessToken();
        String jsapiTicket = WxMnpDriver.getJsSdkGetTicket(accessToken);
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String nonceStr = UUID.randomUUID().toString();
        String signature = WxMnpDriver.buildJSSDKSignature(jsapiTicket,timestamp,nonceStr,url);

        List<String> array = Arrays.asList(
                "onMenuShareTimeline",
                "onMenuShareAppMessage",
                "onMenuShareQQ",
                "onMenuShareWeibo",
                "onMenuShareQZone",
                "openLocation",
                "getLocation",
                "chooseWXPay",
                "updateAppMessageShareData",
                "updateTimelineShareData",
                "openAddress",
                "scanQRCode");

        Map<String,Object> map = new HashMap<>();
        map.put("url", url);
        map.put("jsapi_ticket", jsapiTicket);
        map.put("nonceStr", nonceStr);
        map.put("timestamp", timestamp);
        map.put("signature", signature);
        map.put("appid", appId);
        map.put("jsApiList", array);
        return map;
    }

}

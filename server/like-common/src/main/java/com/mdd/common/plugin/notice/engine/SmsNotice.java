package com.mdd.common.plugin.notice.engine;

import com.alibaba.fastjson.JSON;
import com.mdd.common.plugin.sms.SmsDriver;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.StringUtil;
import com.mdd.common.utils.ToolsUtil;

import java.util.Map;

public class SmsNotice {

    public Boolean send(Map<String, Object> params, Map<String, String> smsTemplate) {
        String mobile = params.getOrDefault("mobile", "").toString();
        String scene  = params.getOrDefault("scene", "").toString();
        if (!StringUtil.isNotEmpty(mobile) || !StringUtil.isNotEmpty(scene)) {
            return false;
        }

//        if (StringUtil.isNotNull(params.get("params"))) {
//            ToolsUtil.objectToMap(params.get("params"));
//        }

//        System.out.println(this.getContent(params, smsTemplate));
        // 发送短信
//        (new SmsDriver())
//                .setMobile(mobile)
//                .setTemplateCode(smsTemplate.getOrDefault("templateId", ""))
//                .setTemplateParam(null)
//                .setSmsContent(this.getContent(params, smsTemplate));

        return  true;
    }

    /**
     * 获取短信内容
     *
     * @author fzr
     * @param params 短信参数
     * @param smsTemplate 短信模板
     * @return String 短信内容
     */
    private String getContent(Map<String, String> params, Map<String, String> smsTemplate) {
        String content = smsTemplate.getOrDefault("content", "");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String searchReplace = "\\$\\{" + entry.getKey() + "}";
            content = content.replaceAll(searchReplace, entry.getValue());
        }

        return content;
    }

    /**
     * 腾讯云参数处理
     *
     * @author fzr
     * @return Map<String, String>
     */
    private Map<String, String> getSmsParams(Map<String, String> params) {
        String engine = ConfigUtil.get("sms", "default", "");
        if (!engine.equals("tencent")) {
            return params;
        }
        return null;
    }

}

package com.mdd.common.plugin.notice.engine;

import com.mdd.common.config.GlobalConfig;
import com.mdd.common.entity.notice.NoticeSetting;
import com.mdd.common.plugin.sms.SmsDriver;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.RedisUtil;
import com.mdd.common.utils.StringUtil;

import java.util.*;

public class SmsNotice {

    /**
     * 发送短信通知
     *
     * @author fzr
     * @param config 基础配置
     * @param params 短信参数
     * @param smsTemplate 短信模板
     * @param noticeSetting 通知设置
     */
    public void send(Map<String, String> config, Map<String, String> params, Map<String, String> smsTemplate, NoticeSetting noticeSetting) {
        String mobile = config.getOrDefault("mobile", "");
        String scene  = config.getOrDefault("scene", "");

        if (StringUtil.isNotEmpty(mobile) && StringUtil.isNotEmpty(scene)) {
            (new SmsDriver())
                    .setMobile(mobile)
                    .setTemplateCode(smsTemplate.getOrDefault("templateId", ""))
                    .setTemplateParam(this.getSmsParams(params, smsTemplate))
                    .setSmsContent(this.getContent(params, smsTemplate))
                    .sendSms();

            // 1=业务通知, 2=验证码
            if (noticeSetting.getType() == 2 && StringUtil.isNotNull(params.get("code"))) {
                String code = params.get("code").toLowerCase();
                RedisUtil.set(GlobalConfig.redisSmsCode+mobile, code);
            }
        }
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
    private Map<String, String> getSmsParams(Map<String, String> params, Map<String, String> smsTemplate) {
        String engine = ConfigUtil.get("sms", "default", "");
        if (!engine.equals("tencent")) {
            return params;
        }

        // 获取内容变量
        List<String> arr = new LinkedList<>();
        String content = smsTemplate.getOrDefault("content", "");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String search = "\\$\\{" + entry.getKey() + "}";
           if (content.indexOf(search) != 1 && !arr.contains(entry.getKey())) {
               arr.add(entry.getKey());
           }
        }

        // 获取变量名称
        List<Integer> arrIndex = new LinkedList<>();
        Map<Integer, String> arr2 = new LinkedHashMap<>();
        if (arr.size() > 0) {
            for (String v: arr) {
                int k = content.indexOf(v);
                arrIndex.add(k);
                arr2.put(k, v);
            }
        }

        // 从小到大排序
        List<String> arr3 = new LinkedList<>();
        Collections.sort(arrIndex);
        for (Integer i : arrIndex) {
            arr3.add(arr2.get(i));
        }

        // 取变量对应值
        Map<String, String> arr4 = new LinkedHashMap<>();
        for (String v : arr3) {
            if (StringUtil.isNotNull(params.get(v))) {
                arr4.put(params.get(v), "");
            }
        }

        return arr4;
    }

}

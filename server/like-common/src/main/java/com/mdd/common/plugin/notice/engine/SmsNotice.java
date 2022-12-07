package com.mdd.common.plugin.notice.engine;

import com.mdd.common.config.GlobalConfig;
import com.mdd.common.plugin.notice.NoticeParams;
import com.mdd.common.plugin.notice.template.SmsTemplate;
import com.mdd.common.plugin.sms.SmsDriver;
import com.mdd.common.util.ConfigUtils;
import com.mdd.common.util.RedisUtils;
import com.mdd.common.util.StringUtils;

import java.util.*;

/**
 * 短信通知
 */
public class SmsNotice {

    /**
     * 发送短信通知
     *
     * @author fzr
     * @param noticeParams  基础配置
     * @param smsTemplate   短信模板
     */
    public void send(NoticeParams noticeParams, SmsTemplate smsTemplate) {
        String mobile = noticeParams.getMobile();
        Integer scene = noticeParams.getScene();

        Map<String, String> params = new LinkedHashMap<>();
        if (StringUtils.isNotNull(noticeParams.getParams())) {
            for (String s : noticeParams.getParams()) {
                String[] arr =  s.split(":");
                String key = arr[0].trim();
                String val = arr[1].trim();
                params.put(key, val);
            }
        }

        if (StringUtils.isNotEmpty(mobile)) {
            (new SmsDriver())
                    .setScene(scene)
                    .setMobile(mobile)
                    .setTemplateCode(smsTemplate.getTemplateId())
                    .setTemplateParam(this.getSmsParams(params, smsTemplate.getContent()))
                    .setSmsContent(this.getContent(params, smsTemplate.getContent()))
                    .sendSms();

            // 通知类型: [1=业务, 2=验证码]
            if (smsTemplate.getType().equals(2) && StringUtils.isNotNull(params.get("code"))) {
                String code = params.get("code").toLowerCase();
                RedisUtils.set(GlobalConfig.redisSmsCode+scene+":"+mobile, code, 900);
            }
        }
    }

    /**
     * 获取短信内容
     *
     * @author fzr
     * @param params 短信参数
     * @param content 短信模板
     * @return String 短信内容
     */
    private String getContent(Map<String, String> params, String content) {
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
     * @param params 短信参数
     * @param content 短信内容
     * @return Map<String, String>
     */
    private Map<String, String> getSmsParams(Map<String, String> params, String content) {
        String engine = ConfigUtils.get("sms", "default", "");
        if (!engine.equals("tencent")) {
            return params;
        }

        // 获取内容变量
        List<String> arr = new LinkedList<>();
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
            if (StringUtils.isNotNull(params.get(v))) {
                arr4.put(params.get(v), "");
            }
        }

        return arr4;
    }

}

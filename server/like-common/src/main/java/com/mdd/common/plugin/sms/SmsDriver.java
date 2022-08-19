package com.mdd.common.plugin.sms;

import com.alibaba.fastjson.JSON;
import com.mdd.common.plugin.sms.engine.AliSms;
import com.mdd.common.plugin.sms.engine.TencentSms;
import com.mdd.common.utils.ConfigUtil;

import java.util.Map;

public class SmsDriver {

    private String mobile;                      // 手机号码
    private String templateId;                  // 短信模板
    private String smsContent;                  // 短信内容
    private Map<String, String> param;          // 短信参数
    private final String engine;                // 短信引擎
    private final Map<String, String> config;   // 短信配置

    /**
     * 构造方法
     */
    public SmsDriver() {
        this.engine = ConfigUtil.get("sms", "default", "aliyun");
        this.config = ConfigUtil.getMap("sms", this.engine);
    }

    /**
     * 设置手机号
     *
     * @author fzr
     * @param mobile 手机号
     * @return SmsDriver
     */
    public SmsDriver setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    /**
     * 设置参数
     *
     * @author fzr
     * @param param 参数
     * @return SmsDriver
     */
    public SmsDriver setParam(Map<String, String> param) {
        this.param = param;
        return this;
    }

    /**
     * 发送短信
     *
     * @author fzr
     */
    public void sendSms() {
        String templateParam = JSON.toJSONString(param);
        switch (this.engine) {
            case "aliyun":
                AliSms aliSms = new AliSms(this.config);
                aliSms.setMobile(this.mobile)
                    .setTemplateId(this.templateId)
                    .setTemplateParams(templateParam)
                    .send();
                break;
            case "tencent":
                TencentSms tencentSms = new TencentSms(this.config);
                tencentSms.setMobile(this.mobile)
                        .setTemplateId(this.templateId)
                        .setTemplateParams(templateParam.split(","))
                        .send();
                break;
        }
    }

}

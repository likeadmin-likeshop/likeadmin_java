package com.mdd.common.plugin.sms;

import com.alibaba.fastjson2.JSON;
import com.mdd.common.exception.OperateException;
import com.mdd.common.plugin.sms.engine.AliSms;
import com.mdd.common.plugin.sms.engine.TencentSms;
import com.mdd.common.util.ConfigUtils;

import java.util.Map;

public class SmsDriver {

    private String mobile;                      // 手机号码
    private String templateCode;                // 短信模板
    private Map<String, String> templateParam;  // 短信参数
    private final String engine;                // 短信引擎
    private final Map<String, String> config;   // 短信配置

    /**
     * 构造方法
     */
    public SmsDriver() {
        this.engine = ConfigUtils.get("sms", "default", "");
        this.config = ConfigUtils.getMap("sms", this.engine);
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
     * 设置模板编号
     *
     * @author fzr
     * @param code 短信编码
     * @return SmsDriver
     */
    public SmsDriver setTemplateCode(String code) {
        this.templateCode = code;
        return this;
    }

    /**
     * 设置模板参数
     *
     * @author fzr
     * @param param 参数
     * @return SmsDriver
     */
    public SmsDriver setTemplateParam(Map<String, String> param) {
        this.templateParam = param;
        return this;
    }

    /**
     * 发送短信
     *
     * @author fzr
     */
    public void sendSms() {
        String templateParam = JSON.toJSONString(this.templateParam);
        Integer sendResult = 0;
        String results = "";

        switch (this.engine) {
            case "aliyun":
                AliSms aliSms = new AliSms(this.config);
                results = aliSms.setMobile(this.mobile)
                    .setTemplateId(this.templateCode)
                    .setTemplateParams(templateParam)
                    .send();

                sendResult = aliSms.getSendResult();
                break;
            case "tencent":
                TencentSms tencentSms = new TencentSms(this.config);
                results = tencentSms.setMobile(this.mobile)
                        .setTemplateId(this.templateCode)
                        .setTemplateParams(this.templateParam)
                        .send();
                sendResult = tencentSms.getSendResult();
                break;
        }

        if (sendResult == 2) {
            throw new OperateException(results);
        }
    }

}

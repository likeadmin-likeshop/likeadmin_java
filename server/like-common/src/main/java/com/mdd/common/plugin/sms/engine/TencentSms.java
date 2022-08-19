package com.mdd.common.plugin.sms.engine;

import com.mdd.common.exception.OperateException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;

import java.util.Map;

/**
 * 腾讯短信驱
 */
public class TencentSms {


    private String mobile;
    private String templateId;
    private String[] templateParams;
    private final Map<String, String> config;

    public TencentSms(Map<String, String> config) {
        this.config = config;
    }

    /**
     * 设置手机号
     * @author fzr
     * @param mobile 手机号码
     * @return AliSms
     */
    public TencentSms setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    /**
     * 设置模板id
     * @author fzr
     * @param templateId 模板id
     * @return AliSms
     */
    public TencentSms setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * 设置模板参数
     * @author fzr
     * @param templateParams 模板参数
     * @return AliSms
     */
    public TencentSms setTemplateParams(String[] templateParams) {
        this.templateParams = templateParams;
        return this;
    }

    /**
     * 发送短信
     *
     * @param config 配置
     * @return String
     */
    public String send() {
        try {
            /*认证对象*/
            Credential cred = new Credential(this.config.get("secret_id").toString(), config.get("secret_key").toString());
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setReqMethod("POST");
            httpProfile.setConnTimeout(60);
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            /*客户端配置*/
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);

            /*参数配置*/
            SmsClient client = new SmsClient(cred, "ap-guangzhou",clientProfile);
            SendSmsRequest req = new SendSmsRequest();
            req.setSignName(config.get("sign").toString());
            req.setSmsSdkAppId(config.get("app_id").toString());
            req.setTemplateId(this.templateId);

            /*手机号码*/
            String[] phoneNumberSet = {"+86"+this.mobile};
            req.setPhoneNumberSet(phoneNumberSet);

            /*模板参数*/
            String[] templateParamSet = this.templateParams;
            req.setTemplateParamSet(templateParamSet);

            /*发起请求*/
            SendSmsResponse res = client.SendSms(req);
            if (!res.getSendStatusSet()[0].getCode().equals("Ok")) {
                throw new Exception(res.getSendStatusSet()[0].getMessage());
            }

            return res.getSendStatusSet()[0].getMessage();
        } catch (Exception e) {
            throw new OperateException("短信发送异常：" + e.getMessage());
        }
    }

}

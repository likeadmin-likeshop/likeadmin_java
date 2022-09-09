package com.mdd.common.plugin.sms.engine;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 腾讯短信驱
 */
public class TencentSms {

    private Integer sendResult;       // 发送结果
    private String mobile;            // 手机号码
    private String templateId;        // 模板编号
    private String[] templateParams;  // 模板参数
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
    public TencentSms setTemplateParams(Map<String, String> templateParams) {
        List<String> params = new LinkedList<>();
        for (Map.Entry<String, String> entry : templateParams.entrySet()) {
            params.add(entry.getKey());

        }
        this.templateParams = params.toArray(new String[0]);
        return this;
    }

    /**
     * 获取发送结果
     *
     * @author fzr
     * @return Integer [1=成功, 2=失败]
     */
    public Integer getSendResult() {
        return this.sendResult;
    }

    /**
     * 发送短信
     *
     * @return String
     */
    public String send() {
        try {
            /*认证对象*/
            Credential cred = new Credential(this.config.get("secretId"), config.get("secretKey"));
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
            req.setSignName(config.get("sign"));
            req.setSmsSdkAppId(config.get("appId"));
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
                this.sendResult = 2;
                return res.getSendStatusSet()[0].getMessage();
            }

            this.sendResult = 1;
            return res.getSendStatusSet()[0].getMessage();
        } catch (Exception e) {
            this.sendResult = 2;
            return e.getMessage();
        }
    }

}

package com.hxkj.common.plugin.sms.engine;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.hxkj.common.exception.OperateException;

import java.util.Map;

/**
 * 阿里云短信
 */
public class AliSms {

    private String mobile;          // 手机号码
    private String templateId;      // 短信模板
    private String templateParams;  // 短信参数
    private final Map<String, String> config; // 短信配置

    /**
     * 构造方法
     *
     * @author fzr
     * @param config 短信配置
     */
    public AliSms(Map<String, String> config) {
        this.config = config;
    }

    /**
     * 设置手机号
     *
     * @author fzr
     * @param mobile 手机号码
     * @return AliSms
     */
    public AliSms setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    /**
     * 设置模板id
     *
     * @author fzr
     * @param templateId 模板id
     * @return AliSms
     */
    public AliSms setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * 设置模板参数
     *
     * @author fzr
     * @param templateParams 模板参数
     * @return AliSms
     */
    public AliSms setTemplateParams(String templateParams) {
        this.templateParams = templateParams;
        return this;
    }

    /**
     * 发送短信
     *
     * @author fzr
     * @return String
     */
    public String send() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", this.config.get("app_key"), this.config.get("secret_key"));
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", this.mobile);
        request.putQueryParameter("SignName", this.config.get("sign"));
        request.putQueryParameter("TemplateCode", this.templateId);
        request.putQueryParameter("TemplateParam", this.templateParams);
        try {
            System.out.println("来来来来来");
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response);
            JSONObject res = JSONObject.parseObject(response.getData());

            if (!res.get("Code").equals("OK") || !res.get("Message").equals("OK")) {
                throw new OperateException(res.get("Message").toString());
            }

            return response.getData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new OperateException("短信发送异常：" + e.getMessage());
        }
    }

}

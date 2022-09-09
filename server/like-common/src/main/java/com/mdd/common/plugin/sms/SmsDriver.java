package com.mdd.common.plugin.sms;

import com.alibaba.fastjson.JSON;
import com.mdd.common.entity.system.SystemLogSms;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.system.SystemLogSmsMapper;
import com.mdd.common.plugin.sms.engine.AliSms;
import com.mdd.common.plugin.sms.engine.TencentSms;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.SpringUtil;

import javax.annotation.Resource;
import javax.management.openmbean.OpenDataException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SmsDriver {

    private final SystemLogSmsMapper systemLogSmsMapper;

    private String mobile;                      // 手机号码
    private String templateCode;                // 短信模板
    private String smsContent = "";             // 短信内容
    private Map<String, String> templateParam;  // 短信参数
    private final String engine;                // 短信引擎
    private final Map<String, String> config;   // 短信配置

    /**
     * 构造方法
     */
    public SmsDriver() {
        this.engine = ConfigUtil.get("sms", "default", "");
        this.config = ConfigUtil.getMap("sms", this.engine);
        this.systemLogSmsMapper = SpringUtil.getBean(SystemLogSmsMapper.class);
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
     * 设置模板内容
     *
     * @author fze
     * @param content 内容
     * @return SmsDriver
     */
    public SmsDriver setSmsContent(String content) {
        this.smsContent = content;
        return this;
    }

    /**
     * 发送短信
     *
     * @author fzr
     */
    public void sendSms() {
        String templateParam = JSON.toJSONString(this.templateParam);
        Integer logId = this.writeSmsLog();
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

        this.updateSmsLog(logId, sendResult, results);
        if (sendResult == 2) {
            throw new OperateException("短信发送失败");
        }
    }

    /**
     * 写入短信日志
     *
     * @author fzr
     */
    private Integer writeSmsLog() {
        SystemLogSms model = new SystemLogSms();
        model.setMobile(this.mobile);
        model.setContent(this.smsContent);
        model.setResults("");
        model.setStatus(0);
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemLogSmsMapper.insert(model);
        return model.getId();
    }

    /**
     * 更新短信日志
     *
     * @author fzr
     * @param id 主键
     * @param status 状态
     * @param result 结果
     */
    private void updateSmsLog(Integer id, Integer status, String result) {
        SystemLogSms model = new SystemLogSms();
        model.setId(id);
        model.setMobile(this.mobile);
        model.setStatus(status);
        model.setResults(result);
        model.setSendTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemLogSmsMapper.updateById(model);
    }

}

package com.mdd.common.plugin.notice.template;

import com.mdd.common.utils.ToolsUtil;
import lombok.Data;

import java.util.Map;

@Data
public class SmsTemplate {

    /** 通知类型: 1=业务,2=验证码 */
    private Integer type;

    /** 短信模板内容 */
    private String templateId;

    /** 短信模板内容 */
    private String content;

    /** 短信模板状态 */
    private Integer status;

    /**
     * 设置参数
     */
    public void setParams(String smsNotice) {
        Map<String, String> config = ToolsUtil.jsonToMap(smsNotice);
        this.setTemplateId(config.getOrDefault("templateId", ""));
        this.setContent(config.getOrDefault("content", ""));
        this.setStatus(Integer.parseInt(config.getOrDefault("status", "0")));
    }

}

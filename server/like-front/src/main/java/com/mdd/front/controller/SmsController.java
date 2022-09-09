package com.mdd.front.controller;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.plugin.notice.NoticeDriver;
import com.mdd.common.utils.ToolsUtil;
import com.mdd.front.validate.SmsParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 短信管理
 */
@RestController
@RequestMapping("/api/sms")
public class SmsController {

    /**
     * 发送短信
     *
     * @author fzr
     * @param smsParam 参数
     * @return Object
     */
    @PostMapping("/send")
    public Object send(@Validated @RequestBody SmsParam smsParam) {
        Map<String, String> config = new LinkedHashMap<>();
        config.put("scene", smsParam.getScene());
        config.put("mobile", smsParam.getMobile());
        Map<String, String> params = new LinkedHashMap<>();
        params.put("code", ToolsUtil.randomInt(4));
        (new NoticeDriver()).handle(config, params);
        return AjaxResult.success();
    }

}

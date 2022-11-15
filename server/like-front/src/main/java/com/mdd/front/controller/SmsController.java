package com.mdd.front.controller;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.plugin.notice.NoticeDriver;
import com.mdd.common.utils.ToolsUtil;
import com.mdd.front.validate.SmsValidate;
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
     * @param smsValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/send")
    public AjaxResult<Object> send(@Validated @RequestBody SmsValidate smsValidate) {
        Map<String, String> config = new LinkedHashMap<>();
        config.put("scene", smsValidate.getScene());
        config.put("mobile", smsValidate.getMobile());
        Map<String, String> params = new LinkedHashMap<>();
        params.put("code", ToolsUtil.randomInt(4));
        (new NoticeDriver()).handle(config, params);
        return AjaxResult.success();
    }

}

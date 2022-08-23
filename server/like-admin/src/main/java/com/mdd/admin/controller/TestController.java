package com.mdd.admin.controller;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.plugin.sms.SmsDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("api/test")
public class TestController {

    @GetMapping("/sms")
    public Object sms() {

        Map<String, String> params = new LinkedHashMap<>();
        // 阿里云的
        params.put("user_name", "小明");
        params.put("order_sn", "22026655656");
        params.put("ff", "eee");
        params.put("gg", "gg");
        params.put("eee", "ee");

        // 腾讯云的


        (new SmsDriver())
                .setMobile("15627119239")
//                .setTemplateCode("SMS_222463029")
                .setTemplateCode("1074928")
                .setTemplateParam(params)
                .sendSms();
        return AjaxResult.success();
    }

}

package com.hxkj.admin.controller;

import com.hxkj.common.core.AjaxResult;

import com.hxkj.common.exception.OperateException;
import com.hxkj.common.plugin.sms.SmsDriver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
public class IndexController {

    @PostMapping("/aa")
    public AjaxResult aa() {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            (new SmsDriver())
                    .setMobile("15627119239")
                    .setParam(params)
                    .sendSms();

            return AjaxResult.success();
        } catch (OperateException e) {
            return AjaxResult.failed(e.getMsg());
        }
    }

}

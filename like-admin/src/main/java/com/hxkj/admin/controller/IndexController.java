package com.hxkj.admin.controller;

import com.hxkj.admin.config.aop.LogAnnotation;
import com.hxkj.common.core.AjaxResult;

import com.hxkj.common.exception.OperateException;
import com.hxkj.common.plugin.sms.SmsDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
public class IndexController {



    @PostMapping("/aa")
    @LogAnnotation(title = "小河")
    public AjaxResult aa(@RequestBody Map<String, String> map) {
        System.out.println("急急急");
        System.out.println(map);
//        try {
//            Map<String, String> params = new LinkedHashMap<>();
//            (new SmsDriver())
//                    .setMobile("15627119239")
//                    .setParam(params)
//                    .sendSms();
//
//            return AjaxResult.success();
//        } catch (OperateException e) {
//            return AjaxResult.failed(e.getMsg());
//        }
        return null;
    }

}

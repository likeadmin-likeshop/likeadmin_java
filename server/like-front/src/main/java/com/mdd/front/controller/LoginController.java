package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.ClientEnum;
import com.mdd.front.service.ILoginService;
import com.mdd.front.validate.RegisterParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Resource
    ILoginService iLoginService;

    /**
     * 注册账号
     *
     * @author fzr
     * @param registerParam 参数
     * @return Object
     */
    @PostMapping("/register")
    public Object register(@Validated @RequestBody RegisterParam registerParam) {
        iLoginService.register(registerParam);
        return AjaxResult.success();
    }

    @PostMapping("/check")
    public Object check(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("scene"), "scene参数缺失!");

        Map<String, Object> map = new LinkedHashMap<>();
        switch (params.get("scene")) {
            case "mnp":
                map = iLoginService.mnpLogin(params);
                break;
            case "sms":
                Assert.isNull(params.get("code"), "code参数缺失!");
                iLoginService.smsLogin(params);
                break;
            case "account":
                iLoginService.accountLogin(params);
                break;
        }

        return AjaxResult.success(map);
    }

}

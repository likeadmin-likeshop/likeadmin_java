package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.core.AjaxResult;
import com.mdd.front.service.ILoginService;
import com.mdd.front.validate.RegParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 登录管理
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Resource
    ILoginService iLoginService;

    /**
     * 注册账号
     *
     * @author fzr
     * @param regParam 参数
     * @return Object
     */
    @PostMapping("/register")
    public Object register(@Validated @RequestBody RegParam regParam) {
        iLoginService.register(regParam);
        return AjaxResult.success();
    }

    /**
     * 登录验证
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/check")
    public Object check(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("scene"), "scene参数缺失!");
        Map<String, Object> map = new LinkedHashMap<>();
        switch (params.get("scene")) {
            case "mnp":
                map = iLoginService.mnpLogin(params);
                break;
            case "mobile":
                map = iLoginService.mobileLogin(params);
                break;
            case "account":
                map = iLoginService.accountLogin(params);
                break;
        }
        return AjaxResult.success(map);
    }

    /**
     * 忘记密码
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/forgotPassword")
    public Object forgotPassword(@RequestBody Map<String, String> params) {
            iLoginService.forgotPassword(params);
            return AjaxResult.success();
    }

}

package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.core.AjaxResult;
import com.mdd.front.service.ILoginService;
import com.mdd.front.validate.RegParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 登录管理
 */
@Slf4j
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
     * 公众号登录
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @GetMapping("/oaLogin")
    public Object oaLogin(@RequestParam Map<String, String> params) {
        Map<String, Object> map = iLoginService.officeLogin(params);
        return AjaxResult.success(map);
    }

    /**
     * 公众号跳转url
     *
     * @author fzr
     * @param url 连接
     * @return Object
     */
    @GetMapping("/codeUrl")
    public Object codeUrl(@RequestParam String url) {
        Assert.notNull(url, "url参数不能为空");
        String uri = iLoginService.codeUrl(url);
        Map<String, String> response = new LinkedHashMap<>();
        response.put("url", uri);
        return AjaxResult.success(response);
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

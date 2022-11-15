package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.core.AjaxResult;
import com.mdd.front.service.ILoginService;
import com.mdd.front.validate.RegValidate;
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
     * @param regValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/register")
    public AjaxResult<Object> register(@Validated @RequestBody RegValidate regValidate) {
        iLoginService.register(regValidate);
        return AjaxResult.success();
    }

    /**
     * 登录验证
     *
     * @author fzr
     * @param params 参数
     * @return AjaxResult<Map<String, Object>>
     */
    @PostMapping("/check")
    public AjaxResult<Map<String, Object>> check(@RequestBody Map<String, String> params) {
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
     * @return AjaxResult<Map<String, Object>>
     */
    @GetMapping("/oaLogin")
    public AjaxResult<Map<String, Object>> oaLogin(@RequestParam Map<String, String> params) {
        Map<String, Object> map = iLoginService.officeLogin(params);
        return AjaxResult.success(map);
    }

    /**
     * 公众号跳转url
     *
     * @author fzr
     * @param url 连接
     * @return AjaxResult<Map<String, String>>
     */
    @GetMapping("/codeUrl")
    public AjaxResult<Map<String, String>> codeUrl(@RequestParam String url) {
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
     * @return AjaxResult<Object>
     */
    @PostMapping("/forgotPassword")
    public AjaxResult<Object> forgotPassword(@RequestBody Map<String, String> params) {
        iLoginService.forgotPassword(params);
        return AjaxResult.success();
    }

}

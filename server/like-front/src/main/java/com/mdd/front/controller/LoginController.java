package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.core.AjaxResult;
import com.mdd.front.service.ILoginService;
import com.mdd.front.validate.UserRegisterValidate;
import com.mdd.front.vo.LoginCodesVo;
import com.mdd.front.vo.LoginTokenVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
     * @param userRegisterValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/register")
    public AjaxResult<Object> register(@Validated @RequestBody UserRegisterValidate userRegisterValidate) {
        iLoginService.register(userRegisterValidate);
        return AjaxResult.success();
    }

    /**
     * 登录验证
     *
     * @author fzr
     * @param params 参数
     * @return AjaxResult<LoginTokenVo>
     */
    @PostMapping("/check")
    public AjaxResult<LoginTokenVo> check(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("scene"), "scene参数缺失!");
        LoginTokenVo vo = new LoginTokenVo();
        switch (params.get("scene")) {
            case "mnp":
                vo = iLoginService.mnpLogin(params);
                break;
            case "mobile":
                vo = iLoginService.mobileLogin(params);
                break;
            case "account":
                vo = iLoginService.accountLogin(params);
                break;
        }
        return AjaxResult.success(vo);
    }

    /**
     * 公众号登录
     *
     * @author fzr
     * @param params 参数
     * @return AjaxResult<LoginTokenVo>
     */
    @GetMapping("/oaLogin")
    public AjaxResult<LoginTokenVo> oaLogin(@RequestParam Map<String, String> params) {
        LoginTokenVo vo = iLoginService.officeLogin(params);
        return AjaxResult.success(vo);
    }

    /**
     * 公众号跳转url
     *
     * @author fzr
     * @param url 连接
     * @return AjaxResult<LoginCodesVo>
     */
    @GetMapping("/codeUrl")
    public AjaxResult<LoginCodesVo> codeUrl(@RequestParam String url) {
        Assert.notNull(url, "url参数不能为空");

        LoginCodesVo vo = new LoginCodesVo();
        vo.setUrl(iLoginService.codeUrl(url));
        return AjaxResult.success(vo);
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

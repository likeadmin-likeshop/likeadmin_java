package com.mdd.front.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.ClientEnum;
import com.mdd.common.util.StringUtils;
import com.mdd.front.service.ILoginService;
import com.mdd.front.validate.login.RegisterValidate;
import com.mdd.front.validate.login.ForgetPwdValidate;
import com.mdd.front.validate.login.OaLoginValidate;
import com.mdd.front.vo.LoginCodesVo;
import com.mdd.front.vo.LoginTokenVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
     * @param registerValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/register")
    public AjaxResult<Object> register(@Validated @RequestBody RegisterValidate registerValidate) {
        iLoginService.register(registerValidate);
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
                Assert.notNull(params.get("code"), "code参数缺失!");
                Assert.notNull(params.get("client"), "client参数缺失!");
                String code    = params.get("code");
                Integer client = Integer.parseInt(params.get("client"));
                vo = iLoginService.mnpLogin(code, client);
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
     * @param oaLoginValidate 参数
     * @return AjaxResult<LoginTokenVo>
     */
    @PostMapping("/oaLogin")
    public AjaxResult<LoginTokenVo> oaLogin(@Validated @RequestBody OaLoginValidate oaLoginValidate) {
        String code = oaLoginValidate.getCode();
        Integer client = oaLoginValidate.getClient();
        client = StringUtils.isNotNull(client) ? client : ClientEnum.OA.getCode();

        LoginTokenVo vo = iLoginService.officeLogin(code, client);
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
     * @param forgetPwdValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/forgotPassword")
    public AjaxResult<Object> forgotPassword(@Validated @RequestBody ForgetPwdValidate forgetPwdValidate) {
        iLoginService.forgotPassword(forgetPwdValidate);
        return AjaxResult.success();
    }

    /**
     * 扫码链接
     *
     * @author fzr
     * @param session session
     * @return AjaxResult<Map<String, String>>
     */
    @GetMapping("/getScanCode")
    public AjaxResult<Map<String, String>> getScanCode(HttpSession session) {
        String qrcodeUrl = iLoginService.getScanCode(session);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("url", qrcodeUrl);
        return AjaxResult.success(map);
    }

}

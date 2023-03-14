package com.mdd.front.controller;

import com.mdd.common.aop.NotLogin;
import com.mdd.common.core.AjaxResult;
import com.mdd.front.LikeFrontThreadLocal;
import com.mdd.front.service.ILoginService;
import com.mdd.front.validate.login.*;
import com.mdd.front.vo.login.LoginUrlsVo;
import com.mdd.front.vo.login.LoginTokenVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

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
     * @param registerValidate 注册参数
     * @return AjaxResult<Object>
     */
    @NotLogin
    @PostMapping("/register")
    public AjaxResult<Object> register(@Validated @RequestBody RegisterValidate registerValidate) {
        Integer terminal = LikeFrontThreadLocal.getTerminal();
        String username = registerValidate.getUsername();
        String password = registerValidate.getPassword();

        iLoginService.register(username, password, terminal);
        return AjaxResult.success();
    }

    /**
     * 账号登录
     *
     * @author fzr
     * @param loginPwdValidate 登录参数
     * @return AjaxResult<LoginTokenVo>
     */
    @NotLogin
    @PostMapping("/accountLogin")
    public AjaxResult<LoginTokenVo> accountLogin(@Validated @RequestBody LoginPwdValidate loginPwdValidate) {
        Integer terminal = LikeFrontThreadLocal.getTerminal();
        String username = loginPwdValidate.getUsername();
        String password = loginPwdValidate.getPassword();

        LoginTokenVo vo = iLoginService.accountLogin(username, password, terminal);
        return AjaxResult.success(vo);
    }

    /**
     * 手机登录
     *
     * @author fzr
     * @param loginPhoneValidate 登录参数
     * @return AjaxResult<LoginTokenVo>
     */
    @NotLogin
    @PostMapping("/mobileLogin")
    public AjaxResult<LoginTokenVo> mobileLogin(@Validated @RequestBody LoginPhoneValidate loginPhoneValidate) {
        Integer terminal = LikeFrontThreadLocal.getTerminal();
        String mobile = loginPhoneValidate.getMobile();
        String code = loginPhoneValidate.getCode();

        LoginTokenVo vo = iLoginService.mobileLogin(mobile, code, terminal);
        return AjaxResult.success(vo);
    }

    /**
     * 微信登录
     *
     * @author fzr
     * @param loginCodeValidate 登录参数
     * @return AjaxResult<LoginTokenVo>
     */
    @NotLogin
    @PostMapping("/mnpLogin")
    public AjaxResult<LoginTokenVo> mnpLogin(@Validated @RequestBody LoginCodeValidate loginCodeValidate) {
        Integer terminal = LikeFrontThreadLocal.getTerminal();
        String code = loginCodeValidate.getCode();

        LoginTokenVo vo = iLoginService.mnpLogin(code, terminal);
        return AjaxResult.success(vo);
    }

    /**
     * 公众号登录
     *
     * @author fzr
     * @param loginCodeValidate 登录参数
     * @return AjaxResult<LoginTokenVo>
     */
    @NotLogin
    @PostMapping("/oaLogin")
    public AjaxResult<LoginTokenVo> oaLogin(@Validated @RequestBody LoginCodeValidate loginCodeValidate) {
        Integer terminal = LikeFrontThreadLocal.getTerminal();
        String code = loginCodeValidate.getCode();

        LoginTokenVo vo = iLoginService.officeLogin(code, terminal);
        return AjaxResult.success(vo);
    }

    /**
     * 公众号链接
     *
     * @author fzr
     * @param url 跳转链接
     * @return AjaxResult<LoginCodesVo>
     */
    @NotLogin
    @GetMapping("/oaCodeUrl")
    public AjaxResult<LoginUrlsVo> oaCodeUrl(@Validated @NotNull() @RequestParam("url") String url) {
        LoginUrlsVo vo = new LoginUrlsVo();
        vo.setUrl(iLoginService.oaCodeUrl(url));
        return AjaxResult.success(vo);
    }

    /**
     * 扫码链接
     *
     * @author fzr
     * @param session session
     * @return AjaxResult<LoginUrlsVo>
     */
    @NotLogin
    @GetMapping("/scanCodeUrl")
    public AjaxResult<LoginUrlsVo> scanCodeUrl(@Validated @NotNull() @RequestParam("url") String url, HttpSession session) {
        String qrcodeUrl = iLoginService.scanCodeUrl(url, session);
        LoginUrlsVo vo = new LoginUrlsVo();
        vo.setUrl(qrcodeUrl);
        return AjaxResult.success(vo);
    }

    /**
     * 扫码登录
     *
     * @author fzr
     * @param loginScanValidate 扫码参数
     * @return AjaxResult<Object>
     */
    @NotLogin
    @PostMapping("/scanLogin")
    public AjaxResult<Object> scanLogin(@Validated @RequestBody LoginScanValidate loginScanValidate, HttpSession session) {
        Integer terminal = LikeFrontThreadLocal.getTerminal();
        String code = loginScanValidate.getCode();
        String state = loginScanValidate.getState();

        LoginTokenVo vo = iLoginService.scanLogin(code, state, terminal, session);
        return AjaxResult.success(vo);
    }

}

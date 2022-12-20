package com.mdd.front.service;

import com.mdd.front.validate.login.RegisterValidate;
import com.mdd.front.validate.login.ForgetPwdValidate;
import com.mdd.front.vo.LoginTokenVo;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录服务接口类
 */
public interface ILoginService {

    /**
     * 账号注册
     *
     * @author fzr
     * @param registerValidate 参数
     */
    void register(RegisterValidate registerValidate);

    /**
     * 账号登录
     *
     * @author fzr
     * @param params 参数
     * @return LoginTokenVo
     */
    LoginTokenVo accountLogin(Map<String, String> params);

    /**
     * 手机登录
     *
     * @author fzr
     * @param params 参数
     * @return LoginTokenVo
     */
    LoginTokenVo mobileLogin(Map<String, String> params);

    /**
     * 微信登录
     *
     * @author fzr
     * @param code 微信code
     * @param client 来源客户端
     * @return LoginTokenVo
     */
    LoginTokenVo mnpLogin(String code, Integer client);

    /**
     * 公众号登录
     *
     * @author fzr
     * @param code 参数
     * @param client 参数
     * @return LoginTokenVo
     */
    LoginTokenVo officeLogin(String code, Integer client);

    /**
     * 公众号跳转url
     *
     * @author fzr
     * @param url 连接
     * @return String
     */
    String codeUrl(String url);

    /**
     * 忘记密码
     *
     * @author fzr
     * @param forgetPwdValidate 参数
     */
    void forgotPassword(ForgetPwdValidate forgetPwdValidate);

    String getScanCode(HttpSession session);
}

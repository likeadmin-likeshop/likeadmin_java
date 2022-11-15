package com.mdd.front.service;

import com.mdd.front.validate.RegValidate;

import java.util.Map;

/**
 * 登录服务接口类
 */
public interface ILoginService {

    /**
     * 账号注册
     *
     * @author fzr
     * @param regValidate 参数
     */
    void register(RegValidate regValidate);

    /**
     * 微信登录
     *
     * @author fzr
     * @param params 参数
     * @return Map<String, Object>
     */
    Map<String, Object> mnpLogin(Map<String, String> params);

    /**
     * 手机登录
     *
     * @author fzr
     * @param params 参数
     * @return Map<String, Object>
     */
    Map<String, Object> mobileLogin(Map<String, String> params);

    /**
     * 账号登录
     *
     * @author fzr
     * @param params 参数
     * @return Map<String, Object>
     */
    Map<String, Object> accountLogin(Map<String, String> params);

    /**
     * 公众号登录
     *
     * @author fzr
     * @param params 参数
     * @return Map<String, Object>
     */
    Map<String, Object> officeLogin(Map<String, String> params);

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
     * @param params 参数
     */
    void forgotPassword(Map<String, String> params);

}

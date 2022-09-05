package com.mdd.front.service;

import com.mdd.front.validate.RegisterParam;

import java.util.Map;

/**
 * 登录服务接口类
 */
public interface ILoginService {

    /**
     * 账号注册
     *
     * @author fzr
     * @param registerParam 参数
     */
    void register(RegisterParam registerParam);

    /**
     * 微信小程序登录
     *
     * @author fzr
     * @param scene 场景
     * @param code 编码
     */
    Map<String, Object> mnpLogin(Map<String, String> params);

    void smsLogin(Map<String, String> params);

    void accountLogin(Map<String, String> params);

    void forgotPassword(Map<String, String> params);
}

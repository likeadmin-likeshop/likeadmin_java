package com.hxkj.admin.service;

import com.hxkj.admin.validate.SysLoginParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 系统登录服务
 */
public interface ISysLoginService {

    /**
     * 登录
     *
     * @author fzr
     * @param sysLoginParam 登录参数
     * @return token
     */
    Map<String, Object> login(SysLoginParam sysLoginParam);

    /**
     * 退出
     *
     * @author fzr
     * @param token 令牌
     */
    void logout(String token);

}

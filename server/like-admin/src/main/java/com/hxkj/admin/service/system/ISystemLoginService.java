package com.hxkj.admin.service.system;

import com.hxkj.admin.validate.system.SystemLoginParam;

import java.util.Map;

/**
 * 系统登录服务接口类
 */
public interface ISystemLoginService {

    /**
     * 登录
     *
     * @author fzr
     * @param systemLoginParam 登录参数
     * @return token
     */
    Map<String, Object> login(SystemLoginParam systemLoginParam);

    /**
     * 退出
     *
     * @author fzr
     * @param token 令牌
     */
    void logout(String token);

}

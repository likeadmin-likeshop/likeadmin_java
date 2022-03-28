package com.hxkj.admin.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 主要是为了将用户信息改为Token传递
 * 重写Token,通过token方式进行验证
 */
public class JwtToken implements AuthenticationToken {

    /**
     * 令牌
     */
    private final String token;

    /**
     * 构造方法
     */
    public JwtToken(String token) {
        super();
        this.token = token;
    }

    /**
     * 获取Token
     */
    public String getToken() {
        return token;
    }

    /**
     * 获取账号
     */
    @Override
    public Object getPrincipal() {
        return token;
    }

    /**
     * 获取密码
     */
    @Override
    public Object getCredentials() {
        return token;
    }

}

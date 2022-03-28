package com.hxkj.admin.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 主要是为了将用户信息改为Token传递
 * 重写Token,通过token方式进行验证
 */
public class JwtToken implements AuthenticationToken {

    private final String token;

    public JwtToken(String token) {
        System.out.println("就将计就计");
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}

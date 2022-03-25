package com.hxkj.admin.config;

import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class RealmConfig extends AuthorizingRealm {

    /**
     * 授权
     * @param principals 主要
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");

        // 拿到当前登录对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        //info.addStringPermission(currentUser.getPerms());

        return info;
    }

    /**
     * 认证
     * @param token 令牌
     * @return AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = "root";
        String password = "123456";

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        if (!userToken.getUsername().equals(name)) {
            return null;
        }

        // 密码认证
        return new SimpleAuthenticationInfo("", password, "");
    }

}

package com.hxkj.admin.config.shiro;

import com.hxkj.admin.service.ISysAdminService;
import com.hxkj.common.utils.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Resource
    ISysAdminService iSysAdminService;

    /**
     * 重写判断token是否是JwtToke
     *
     * @param token AuthenticationToken
     * @return boolean
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权: 根据认证数据验证用户权限
     *
     * @param principals 包含了所有已认证的安全数据
     * @return AuthorizationInfo 授权数据
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");

        Set<String> perms = new HashSet<>();
        Set<String> roles = new HashSet<>();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(perms);
        info.addRoles(roles);

        return info;
    }

    /**
     * 认证: 校验用户名和密码是否一致
     *
     * @param authenticationToken token
     * @return AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        JwtToken jwtToken = (JwtToken) authenticationToken;

        String token = jwtToken.getToken();
        Integer userId = JwtUtil.getAudience(token);

        if (userId == null || userId <= 0) {
            throw new AuthenticationException("用户异常");
        }

        return new SimpleAuthenticationInfo(token, token, "shiroRealm");
    }

}

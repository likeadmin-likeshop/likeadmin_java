package com.hxkj.admin.config;

import com.hxkj.admin.service.ISysAdminService;
import com.hxkj.common.entity.system.SysAdmin;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    @Resource
    ISysAdminService iSysAdminService;


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
        // 1、获取用户安全数据
        Integer adminId = (Integer) principals.getPrimaryPrincipal();

        // 2、根据用户ID查询用户

        // 3、查询用户角色权限
        List<String> perms = new ArrayList<>();
        perms.add("user:add");
        perms.add("user:update");

        List<String> roles = new ArrayList<>();
        roles.add("role1");
        roles.add("role2");

        // 4、构造返回
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(perms);
        info.addRoles(roles);

        return info;
    }

    /**
     * 认证: 校验用户名和密码是否一致
     *
     * @param auth 令牌
     * @return AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) {

        System.out.println("靠靠靠靠靠靠靠");
        String token = (String) auth.getCredentials();

        // 登录参数
//        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
//        String username = upToken.getUsername();
//        String password = new String(upToken.getPassword());
//
//        // 验证用户
//        SysAdmin sysAdmin = iSysAdminService.findByUsername(username);
//        if (sysAdmin == null) {
//            return null;
//        } else if (!password.equals(sysAdmin.getPassword())) {
//            return null;
//        }

        // 登录成功
        return new SimpleAuthenticationInfo(1, "13", "shiroRealm");
    }

}

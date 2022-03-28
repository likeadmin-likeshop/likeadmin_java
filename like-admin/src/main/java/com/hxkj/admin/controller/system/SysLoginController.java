package com.hxkj.admin.controller.system;

import com.hxkj.admin.validate.SysLoginParam;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
public class SysLoginController {

    /**
     * 登录系统
     *
     * @author fzr
     * @param sysLoginParam 登录参数
     * @return Object
     */
    @PostMapping("/login")
    public Object login(@Validated() @RequestBody SysLoginParam sysLoginParam) {


        String username = sysLoginParam.getUsername();
        String password = sysLoginParam.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        System.out.println("斤斤计较");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return AjaxResult.success();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (!e.getMessage().equals("")) {
                msg = e.getMessage();
            }
            return AjaxResult.failed(msg);
        }
    }

}

package com.hxkj.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.hxkj.common.entity.system.SysAdmin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {


    @GetMapping("/user/add")
    public void index() {
        QueryWrapper<SysAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("id = 1");


    }

    @GetMapping("/user/update")
    public void ee() {
        System.out.println("小红");
    }

    @RequestMapping("/login")
    public String login(String username, String password) {
        System.out.println(username);
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();

        // 用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 执行登录方法
        try {
            subject.login(token);
            return "登录成功";
        } catch (UnknownAccountException e) {
            return "用户名错误";
        } catch (IncorrectCredentialsException e) {
            return "密码错误";
        }
    }

}

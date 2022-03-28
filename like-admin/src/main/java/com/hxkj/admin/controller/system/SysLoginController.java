package com.hxkj.admin.controller.system;

import com.hxkj.admin.config.shiro.JwtToken;
import com.hxkj.admin.service.ISysAdminService;
import com.hxkj.admin.validate.SysLoginParam;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.entity.system.SysAdmin;
import com.hxkj.common.utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
public class SysLoginController {

    @Resource
    ISysAdminService iSysAdminService;

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

        // 查询用户
        SysAdmin sysAdmin = iSysAdminService.findByUsername(username);
        if (sysAdmin == null || sysAdmin.getIsDelete() == 1) {
            return AjaxResult.failed("账号或密码错误");
        }

        // 生成令牌
        String tokenStr = JwtUtil.createToken(sysAdmin.getId());
        JwtToken token = new JwtToken(tokenStr);

        // 登录用户
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        return AjaxResult.success("登录成功", tokenStr);
    }

}

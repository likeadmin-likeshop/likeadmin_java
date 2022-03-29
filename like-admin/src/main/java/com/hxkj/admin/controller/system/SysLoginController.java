package com.hxkj.admin.controller.system;

import com.hxkj.admin.service.ISysLoginService;
import com.hxkj.admin.validate.SysLoginParam;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.exception.LoginException;
import com.hxkj.common.exception.OperateException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
public class SysLoginController {

    @Resource
    ISysLoginService iSysLoginService;

    /**
     * 登录系统
     *
     * @author fzr
     * @param sysLoginParam 登录参数
     * @return Object
     */
    @PostMapping("/login")
    public Object login(@Validated() @RequestBody SysLoginParam sysLoginParam) {
        try {
            Map<String, Object> map = iSysLoginService.login(sysLoginParam);
            return AjaxResult.success(map);
        } catch (LoginException e) {
            return AjaxResult.failed(e.getCode(), e.getMsg());
        } catch (OperateException e) {
            return AjaxResult.failed(e.getMsg());
        }
    }

    /**
     * 退出登录
     *
     * @author fzr
     * @param request 请求接口
     * @return Object
     */
    @PostMapping("/logout")
    public Object logout(HttpServletRequest request) {
        try {
            iSysLoginService.logout(request.getHeader("token"));
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.failed(e.getMessage());
        }
    }

}

package com.mdd.admin.controller.system;

import com.mdd.common.aop.NotLogin;
import com.mdd.common.aop.NotPower;
import com.mdd.admin.service.ISystemLoginService;
import com.mdd.admin.validate.system.SystemAdminLoginsValidate;
import com.mdd.admin.vo.system.SystemCaptchaVo;
import com.mdd.admin.vo.system.SystemLoginVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统登录管理
 */
@RestController
@RequestMapping("api/system")
public class SystemLoginController {

    @Resource
    ISystemLoginService iSystemLoginService;

    /**
     * 验证码
     *
     * @author fzr
     * @return AjaxResult<SystemCaptchaVo>
     */
    @NotLogin
    @GetMapping("/captcha")
    public AjaxResult<SystemCaptchaVo> captcha() {
        SystemCaptchaVo vo = iSystemLoginService.captcha();
        return AjaxResult.success(vo);
    }

    /**
     * 登录系统
     *
     * @author fzr
     * @param loginsValidate 登录参数
     * @return AjaxResult<SystemLoginVo>
     */
    @NotLogin
    @PostMapping("/login")
    public AjaxResult<SystemLoginVo> login(@Validated() @RequestBody SystemAdminLoginsValidate loginsValidate) {
        SystemLoginVo vo = iSystemLoginService.login(loginsValidate);
        return AjaxResult.success(vo);
    }

    /**
     * 退出登录
     *
     * @author fzr
     * @param request 请求接口
     * @return AjaxResult<Object>
     */
    @NotPower
    @PostMapping("/logout")
    public AjaxResult<Object> logout(HttpServletRequest request) {
        iSystemLoginService.logout(request.getHeader("token"));
        return AjaxResult.success();
    }

}

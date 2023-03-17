package com.mdd.admin.controller.system;

import com.mdd.common.aop.NotLogin;
import com.mdd.common.aop.NotPower;
import com.mdd.admin.service.ISystemLoginService;
import com.mdd.admin.validate.system.SystemAdminLoginsValidate;
import com.mdd.admin.vo.system.SystemCaptchaVo;
import com.mdd.admin.vo.system.SystemLoginVo;
import com.mdd.common.core.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/system")
@Api(tags = "系统登录管理")
public class SystemLoginController {

    @Resource
    ISystemLoginService iSystemLoginService;

    @NotLogin
    @GetMapping("/captcha")
    @ApiOperation(value="取验证码")
    public AjaxResult<SystemCaptchaVo> captcha() {
        SystemCaptchaVo vo = iSystemLoginService.captcha();
        return AjaxResult.success(vo);
    }

    @NotLogin
    @PostMapping("/login")
    @ApiOperation(value="登录系统")
    public AjaxResult<SystemLoginVo> login(@Validated() @RequestBody SystemAdminLoginsValidate loginsValidate) {
        SystemLoginVo vo = iSystemLoginService.login(loginsValidate);
        return AjaxResult.success(vo);
    }

    @NotPower
    @PostMapping("/logout")
    @ApiOperation(value="退出登录")
    public AjaxResult<Object> logout(HttpServletRequest request) {
        iSystemLoginService.logout(request.getHeader("token"));
        return AjaxResult.success();
    }

}

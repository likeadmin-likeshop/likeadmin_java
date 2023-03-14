package com.mdd.front.validate.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 账号密码登录参数
 */
@Data
public class LoginPwdValidate {

    @NotNull(message = "username参数缺失")
    @NotEmpty(message = "账号不能为空")
    private String username;

    @NotNull(message = "password参数缺失")
    @NotEmpty(message = "密码不能为空")
    private String password;

}

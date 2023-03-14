package com.mdd.front.validate.users;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 忘记密码参数
 */
@Data
public class UserForgetPwdValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "mobile参数缺失")
    @NotEmpty(message = "手机号不能为空")
    private String mobile;

    @NotNull(message = "code参数缺失")
    @NotEmpty(message = "验证码不能为空")
    private String code;

    @NotNull(message = "password参数缺失")
    @NotEmpty(message = "新密码不能为空")
    private String password;

}
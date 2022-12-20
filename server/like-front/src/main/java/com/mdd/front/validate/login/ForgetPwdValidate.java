package com.mdd.front.validate.login;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 忘记密码参数
 */
@Data
public class ForgetPwdValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "mobile参数缺失")
    private String mobile;

    @NotNull(message = "code参数缺失")
    private String code;

    @NotNull(message = "password参数缺失")
    private String password;

}
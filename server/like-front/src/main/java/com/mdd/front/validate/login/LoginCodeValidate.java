package com.mdd.front.validate.login;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 微信登录参数
 */
@Data
public class LoginCodeValidate {

    @NotNull(message = "code参数缺失")
    private String code;

}

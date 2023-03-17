package com.mdd.front.validate.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("微信登录参数")
public class LoginCodeValidate {

    @NotNull(message = "code参数缺失")
    @ApiModelProperty(value = "微信code", required = true)
    private String code;

}

package com.mdd.front.validate.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@ApiModel("注册账号参数")
public class RegisterValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "username参数缺失")
    @NotEmpty(message = "账号不能为空")
    @Length(min = 3, max = 12, message = "账号必须在3~12个字符内")
    @Pattern(message = "账号应该为3-12位数字、字母组合", regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{3,12}$")
    @ApiModelProperty(value = "登录账号", required = true)
    private String username;

    @NotNull(message = "password参数缺失")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 12, message = "密码必须在6~12个字符内")
    @ApiModelProperty(value = "登录密码", required = true)
    private String password;

}

package com.mdd.front.validate.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@ApiModel("修改密码参数")
public class UserChangePwdValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "password参数缺失")
    @Pattern(message = "密码必须是6-20字母+数字组合!", regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")
    @ApiModelProperty(value = "新密码", required = true)
    private String password;

    @NotNull(message = "oldPassword参数缺失")
    @NotNull(message = "旧密码不能为空")
    @ApiModelProperty(value = "旧密码", required = true)
    private String oldPassword="";

}

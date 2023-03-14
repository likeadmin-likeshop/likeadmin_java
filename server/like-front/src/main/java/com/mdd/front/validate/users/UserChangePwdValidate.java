package com.mdd.front.validate.users;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 用户修改密码参数
 */
@Data
public class UserChangePwdValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "password参数缺失")
    @Pattern(message = "密码必须是6-20字母+数字组合!", regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")
    private String password;

    @NotNull(message = "oldPassword参数缺失")
    @NotNull(message = "旧密码不能为空")
    private String oldPassword="";

}

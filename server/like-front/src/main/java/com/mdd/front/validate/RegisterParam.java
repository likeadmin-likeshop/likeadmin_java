package com.mdd.front.validate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 注册参数类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RegisterParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "username参数缺失")
    @NotEmpty(message = "账号不能为空")
    @Length(min = 3, max = 12, message = "账号必须在3~12个字符内")
    @Pattern(message = "账号只允许是字母和数字", regexp="^[A-Za-z0-9]+$")
    @Pattern(message = "账号应该为3-12位字母、数字组合", regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{3,12}$")
    private String username;

    @NotNull(message = "password参数缺失")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 12, message = "密码必须在6~12个字符内")
    private String password;

}

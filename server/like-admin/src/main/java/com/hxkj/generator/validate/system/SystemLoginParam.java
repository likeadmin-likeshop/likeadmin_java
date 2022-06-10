package com.hxkj.generator.validate.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 系统登录参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemLoginParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "账号不能为空")
    @Length(min = 2, max = 20, message = "账号或密码错误")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 18, message = "账号或密码错误")
    private String password;

}

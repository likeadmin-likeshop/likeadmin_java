package com.mdd.admin.validate.system;

import com.mdd.admin.validate.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * 系统登录参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemLoginParam extends BaseParam {

    @NotEmpty(message = "账号不能为空")
    @Length(min = 2, max = 20, message = "账号或密码错误")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 18, message = "账号或密码错误")
    private String password;

}

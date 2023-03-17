package com.mdd.front.validate.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ApiModel("手机号登录参数")
public class LoginPhoneValidate {

    @NotNull(message = "mobile参数缺失")
    @NotEmpty(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号只能为11位")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;

    @NotNull(message = "code参数缺失")
    @NotEmpty(message = "code不能为空")
    @Length(min = 4, max = 6, message = "验证码长度不符合")
    @ApiModelProperty(value = "验证码", required = true)
    private String code;

}

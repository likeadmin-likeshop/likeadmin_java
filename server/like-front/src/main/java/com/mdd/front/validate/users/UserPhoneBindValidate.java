package com.mdd.front.validate.users;

import com.mdd.common.validator.annotation.StringContains;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 绑定或修改手机参数
 */
@Data
public class UserPhoneBindValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "type参数缺失")
    @StringContains(values = {"bind", "change"})
    private String type;

    @NotNull(message = "mobile参数缺失")
    @NotEmpty(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号只能为11位")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String mobile;

    @NotNull(message = "code参数缺失")
    @NotEmpty(message = "验证码不能为空")
    private String code;

}

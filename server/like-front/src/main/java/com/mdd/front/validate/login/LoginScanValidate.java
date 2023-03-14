package com.mdd.front.validate.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 网站扫码登录验证
 */
@Data
public class LoginScanValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "code参数缺失")
    @NotEmpty(message = "code不能为空")
    private String code;

    @NotNull(message = "二维码已失效或不存在,请重新操作")
    @NotEmpty(message = "二维码已失效或不存在,请重新操作")
    private String state;

}

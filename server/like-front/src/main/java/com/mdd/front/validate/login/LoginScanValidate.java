package com.mdd.front.validate.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("扫码登录验证")
public class LoginScanValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "code参数缺失")
    @NotEmpty(message = "code不能为空")
    @ApiModelProperty(value = "微信code", required = true)
    private String code;

    @NotNull(message = "二维码已失效或不存在,请重新操作")
    @NotEmpty(message = "二维码已失效或不存在,请重新操作")
    @ApiModelProperty(value = "state码", required = true)
    private String state;

}

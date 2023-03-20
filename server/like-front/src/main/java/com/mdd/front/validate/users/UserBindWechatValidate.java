package com.mdd.front.validate.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("绑定微信小程序或公众号")
public class UserBindWechatValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "code参数缺失")
    @ApiModelProperty(value = "微信code", required = true)
    private String code;

}

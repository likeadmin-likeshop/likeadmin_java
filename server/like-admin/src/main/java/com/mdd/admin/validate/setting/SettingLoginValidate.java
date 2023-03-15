package com.mdd.admin.validate.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("登录信息设置参数")
public class SettingLoginValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录方式")
    private String loginWay = "";

    @ApiModelProperty(value = "强制绑定手机")
    private Integer forceBindMobile = 0;

    @ApiModelProperty(value = "政策协议")
    private Integer openAgreement = 0;

    @ApiModelProperty(value = "第三方登录")
    private Integer openOtherAuth = 0;

    @ApiModelProperty(value = "微信开放平台")
    private String autoLoginAuth = "";

}

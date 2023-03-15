package com.mdd.admin.vo.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("登录设置Vo")
public class SettingLoginVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录方式")
    private List<Integer> loginWay;

    @ApiModelProperty(value = "强制绑定手机")
    private Integer forceBindMobile;

    @ApiModelProperty(value = "是否开启协议")
    private Integer openAgreement;

    @ApiModelProperty(value = "第三方的登录")
    private Integer openOtherAuth;

    @ApiModelProperty(value = "自动登录授权")
    private List<Integer> autoLoginAuth;

}

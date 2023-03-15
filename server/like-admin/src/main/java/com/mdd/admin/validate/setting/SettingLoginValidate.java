package com.mdd.admin.validate.setting;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("登录信息设置参数")
public class SettingLoginValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String loginWay = "";

    private Integer forceBindMobile = 0;

    private Integer openAgreement = 0;

    private Integer openOtherAuth = 0;

    private String autoLoginAuth = "";

}

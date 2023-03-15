package com.mdd.admin.validate.setting;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("用户设置参数")
public class SettingUserValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String defaultAvatar = "";

}

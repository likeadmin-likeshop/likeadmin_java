package com.mdd.admin.validate.setting;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户设置参数
 */
@Data
public class SettingUserValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String defaultAvatar = "";

}

package com.mdd.admin.vo.setting;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户设置Vo
 */
@Data
public class SettingUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String defaultAvatar;

}

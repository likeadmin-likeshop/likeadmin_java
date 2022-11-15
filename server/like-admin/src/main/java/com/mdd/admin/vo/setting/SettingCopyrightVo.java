package com.mdd.admin.vo.setting;

import lombok.Data;

import java.io.Serializable;

/**
 * 版权设置Vo
 */
@Data
public class SettingCopyrightVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String link;

}

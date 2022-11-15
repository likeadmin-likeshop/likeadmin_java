package com.mdd.admin.vo.setting;

import lombok.Data;

import java.io.Serializable;

/**
 * 政策协议设置对象Vo
 */
@Data
public class SettingProtocolObjectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String content;

}

package com.mdd.admin.vo.setting;

import lombok.Data;

import java.io.Serializable;

/**
 * 政策协议详情Vo
 */
@Data
public class SettingProtocolDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private SettingProtocolObjectVo service;
    private SettingProtocolObjectVo privacy;

}

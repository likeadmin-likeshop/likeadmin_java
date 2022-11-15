package com.mdd.admin.validate.setting;

import com.mdd.admin.vo.setting.SettingProtocolObjectVo;
import lombok.Data;

import java.io.Serializable;

/**
 * 政策协议设置参数
 */
@Data
public class SettingProtocolValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private SettingProtocolObjectVo service;

    private SettingProtocolObjectVo privacy;

}

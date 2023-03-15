package com.mdd.admin.vo.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("政策协议详情Vo")
public class SettingProtocolDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务协议")
    private SettingProtocolObjectVo service;

    @ApiModelProperty(value = "隐私协议")
    private SettingProtocolObjectVo privacy;

}

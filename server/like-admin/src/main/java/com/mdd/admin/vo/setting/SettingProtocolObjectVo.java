package com.mdd.admin.vo.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("政策协议设置对象Vo")
public class SettingProtocolObjectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "协议名称")
    private String name;

    @ApiModelProperty(value = "协议内容")
    private String content;

}

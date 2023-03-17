package com.mdd.admin.validate.channel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("开发平台渠道参数")
public class ChannelOpValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "appId")
    private String appId;

    @ApiModelProperty(value = "appSecret")
    private String appSecret;

}

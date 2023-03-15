package com.mdd.admin.vo.channel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("开发平台Vo")
public class ChannelOpVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "appId")
    private String appId;

    @ApiModelProperty(value = "appSecret")
    private String appSecret;

}

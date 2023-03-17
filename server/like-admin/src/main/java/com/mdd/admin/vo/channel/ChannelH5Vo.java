package com.mdd.admin.vo.channel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("H5渠道Vo")
public class ChannelH5Vo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否关闭")
    private Integer status;

    @ApiModelProperty(value = "关闭类型")
    private Integer close;

    @ApiModelProperty(value = "关闭访问")
    private String url;

}

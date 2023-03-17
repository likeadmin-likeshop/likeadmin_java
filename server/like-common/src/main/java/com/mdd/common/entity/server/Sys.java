package com.mdd.common.entity.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("系统相关信息实体")
public class Sys implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("服务器名称")
    private String computerName;

    @ApiModelProperty("服务器Ip")
    private String computerIp;

    @ApiModelProperty("项目路径")
    private String userDir;

    @ApiModelProperty("操作系统")
    private String osName;

    @ApiModelProperty("系统架构")
    private String osArch;

}

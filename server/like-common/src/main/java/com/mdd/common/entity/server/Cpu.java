package com.mdd.common.entity.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("CPU相关信息实体")
public class Cpu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("核心数")
    private int cpuNum;

    @ApiModelProperty("CPU总的使用率")
    private double total;

    @ApiModelProperty("CPU系统使用率")
    private double sys;

    @ApiModelProperty("CPU用户使用率")
    private double used;

    @ApiModelProperty("CPU当前等待率")
    private double wait;

    @ApiModelProperty("CPU当前空闲率")
    private double free;

}

package com.mdd.common.entity.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("JVM相关信息实体")
public class Jvm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("当前JVM占用的内存总数(M)")
    private double total;

    @ApiModelProperty("JVM最大可用内存总数(M)")
    private double max;

    @ApiModelProperty("JVM空闲内存(M)")
    private double free;

    @ApiModelProperty("JVM内存使用率")
    private double usage;

    @ApiModelProperty("JDK版本")
    private String version;

    @ApiModelProperty("JDK路径")
    private String home;

    @ApiModelProperty("JDK名称")
    private String name;

    @ApiModelProperty("运行参数")
    private String inputArgs;

    @ApiModelProperty("JDK运行时间")
    private String runTime;

    @ApiModelProperty("JDK启动时间")
    private String startTime;
}

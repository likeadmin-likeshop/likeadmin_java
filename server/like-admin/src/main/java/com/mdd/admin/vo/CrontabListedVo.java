package com.mdd.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("计划任务列表Vo")
public class CrontabListedVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务ID")
    private Integer id;           // 执行ID

    @ApiModelProperty(value = "任务分组")
    private String groups;

    @ApiModelProperty(value = "任务名称")
    private String name;

    @ApiModelProperty(value = "执行命令")
    private String command;

    @ApiModelProperty(value = "执行规则")
    private String rules;

    @ApiModelProperty(value = "错误信息")
    private String error;

    @ApiModelProperty(value = "执行状态: [1=正在运行, 2=任务停止, 3=发生错误]")
    private Integer status;

    @ApiModelProperty(value = "执行策略: [1=立即执行, 2=执行一次, 3=放弃执行]")
    private Integer strategy;

    @ApiModelProperty(value = "并发执行: [0=否, 1=是]")
    private Integer concurrent;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "执行耗时")
    private Long taskTime;

}

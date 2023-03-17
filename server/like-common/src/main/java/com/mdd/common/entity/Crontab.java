package com.mdd.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("计划任务实体")
public class Crontab implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("任务名称")
    private String name;

    @ApiModelProperty("任务分组")
    private String types;

    @ApiModelProperty("执行命令")
    private String command;

    @ApiModelProperty("执行规则")
    private String rules;

    @ApiModelProperty("备注信息")
    private String remark;

    @ApiModelProperty("错误信息")
    private String error;

    @ApiModelProperty(" 执行状态: 1=正在运行, 2=任务停止, 3=发生错误")
    private Integer status;

    @ApiModelProperty("执行策略: 1=立即执行, 2=执行一次, 3=放弃执行")
    private Integer strategy;

    @ApiModelProperty("并发执行: 0=否, 1=是")
    private Integer concurrent;

    @ApiModelProperty("是否删除: 0=否, 1=是")
    private Integer isDelete;

    @ApiModelProperty("开始时间")
    private Long startTime;

    @ApiModelProperty("结束时间")
    private Long endTime;

    @ApiModelProperty("任务耗时")
    private Long taskTime;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("删除时间")
    private Long deleteTime;

}

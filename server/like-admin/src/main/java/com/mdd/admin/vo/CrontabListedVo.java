package com.mdd.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 计划任务列表Vo
 */
@Data
public class CrontabListedVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;           // 执行ID
    private String groups;        // 执行分组
    private String name;          // 执行名称
    private String command;       // 执行命令
    private String rules;         // 执行规则
    private String error;         // 错误信息
    private Integer status;       // 执行状态: 1=正在运行, 2=任务停止, 3=发生错误
    private Integer strategy;     // 执行策略: 1=立即执行, 2=执行一次, 3=放弃执行
    private Integer concurrent;   // 并发执行: 0=否, 1=是
    private String startTime;     // 开始时间
    private String endTime;       // 结束时间
    private Long taskTime;        // 执行耗时

}

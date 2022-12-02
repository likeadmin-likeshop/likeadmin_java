package com.mdd.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 计划任务详情Vo
 */
@Data
public class CrontabDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;         // 任务主键
    private String groups;      // 任务分组
    private String name;        // 任务名称
    private String command;     // 执行命令
    private String rules;       // 执行规则
    private String remark;      // 备注信息
    private String error;       // 错误信息
    private Integer status;     // 执行状态: 1=正在运行, 2=任务停止, 3=发生错误
    private Integer strategy;   // 执行策略: 1=立即执行, 2=执行一次, 3=放弃执行
    private Integer concurrent; // 并发执行: 0=否, 1=是

}

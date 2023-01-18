package com.mdd.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 计划任务实体
 */
@Data
public class Crontab implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;         // 主键
    private String name;        // 任务名称
    private String types;      // 任务分组
    private String command;     // 执行命令
    private String rules;       // 执行规则
    private String remark;      // 备注信息
    private String error;       // 错误信息
    private Integer status;     // 执行状态: 1=正在运行, 2=任务停止, 3=发生错误
    private Integer strategy;   // 执行策略: 1=立即执行, 2=执行一次, 3=放弃执行
    private Integer concurrent; // 并发执行: 0=否, 1=是
    private Integer isDelete;   // 是否删除: 0=否, 1=是
    private Long startTime;     // 开始时间
    private Long endTime;       // 结束时间
    private Long taskTime;      // 任务耗时
    private Long createTime;    // 创建时间
    private Long updateTime;    // 更新时间
    private Long deleteTime;    // 删除时间

}

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
    private Integer id;       // 主键
    private String name;      // 任务名称
    private String command;   // 任务命令
    private String rules;     // 任务规则
    private String remark;    // 备注信息
    private Integer status;   // 执行状态：1=运行, 2-停止, 3=错误
    private Integer isDelete; // 是否删除: 0=否, 1=是
    private Long createTime;  // 创建时间
    private Long updateTime;  // 更新时间
    private Long deleteTime;  // 删除时间

}

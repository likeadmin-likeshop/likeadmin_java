package com.mdd.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统操作日志实体
 */
@Data
public class SystemLogOperate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;         // 主键
    private Integer adminId;    // 操作人ID
    private String type;        // 请求类型: GET/POST/PUT
    private String title;       // 操作标题
    private String method;      // 请求方法
    private String ip;          // 请求IP
    private String url;         // 请求接口
    private String args;        // 请求参数
    private String error;       // 错误信息
    private Integer status;     // 执行状态: [1=成功, 2=失败]
    private Long startTime;     // 开始时间
    private Long endTime;       // 结束时间
    private Long taskTime;      // 执行耗时
    private Long createTime;    // 创建时间

}

package com.mdd.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 操作日志Vo
 */
@Data
public class SystemLogOperateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;         // 主键
    private String username;    // 用户账号
    private String nickname;    // 用户昵称
    private String type;        // 请求类型: GET/POST/PUT
    private String title;       // 操作标题
    private String method;      // 请求方式
    private String ip;          // 请求IP
    private String url;         // 请求地址
    private String args;        // 请求参数
    private String error;       // 错误信息
    private Integer status;     // 执行状态: [1=成功, 2=失败]
    private String taskTime;    // 执行耗时
    private String startTime;   // 开始时间
    private String endTime;     // 结束时间
    private String createTime;  // 创建时间

}

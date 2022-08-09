package com.hxkj.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录日志Vo
 */
@Data
public class LogLoginVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;         // 主键
    private String username;    // 用户名称
    private String ip;          // 来源IP
    private String os;          // 终端设备
    private String browser;     // 浏览器UA
    private Integer status;     // 操作状态: [0=失败, 1=成功]
    private String createTime;  // 创建时间

}

package com.mdd.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统登录日志实体
 */
@Data
public class SystemLogLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;       // 主键
    private Integer adminId;  // 管理员ID
    private String username;  // 登录账号
    private String ip;        // 登录IP
    private String os;        // 操作系统
    private String browser;   // 浏览器
    private Integer status;   // 操作状态: [1=成功, 2=失败]
    private Long createTime;  // 创建时间

}

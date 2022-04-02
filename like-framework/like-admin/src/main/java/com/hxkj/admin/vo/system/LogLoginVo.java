package com.hxkj.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录日志Vo
 */
@Data
public class LogLoginVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String ip;
    private String os;
    private String browser;
    private String address;
    private Integer status;
    private String createTime;

}

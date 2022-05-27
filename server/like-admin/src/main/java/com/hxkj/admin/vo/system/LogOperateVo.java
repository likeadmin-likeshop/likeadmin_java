package com.hxkj.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 操作日志Vo
 */
@Data
public class LogOperateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String nickname;
    private String type;
    private String title;
    private String method;
    private String ip;
    private String url;
    private String args;
    private String error;
    private Integer status;
    private String taskTime;
    private String startTime;
    private String endTime;
    private String createTime;

}

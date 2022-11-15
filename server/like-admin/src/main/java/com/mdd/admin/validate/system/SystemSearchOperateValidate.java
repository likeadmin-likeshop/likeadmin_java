package com.mdd.admin.validate.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统操作日志搜索参数
 */
@Data
public class SystemSearchOperateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String username;

    private String ip;

    private String type;

    private String status;

    private String url;

    private String startTime;

    private String endTime;

}

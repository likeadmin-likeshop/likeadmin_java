package com.mdd.admin.validate.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统登录日志搜索参数
 */
@Data
public class SystemSearchLoginsValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private Integer status;

    private String startTime;

    private String endTime;

}

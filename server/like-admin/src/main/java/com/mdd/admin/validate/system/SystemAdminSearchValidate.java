package com.mdd.admin.validate.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统管理员搜索参数
 */
@Data
public class SystemAdminSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String nickname;

    private Integer role;

}

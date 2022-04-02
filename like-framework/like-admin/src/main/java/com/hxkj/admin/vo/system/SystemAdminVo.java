package com.hxkj.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 管理员Vo
 */
@Data
public class SystemAdminVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String nickname;
    private String avatar;
    private String role;
    private Integer isDisable;
    private String lastLoginIp;
    private String lastLoginTime;
    private String createTime;
    private String updateTime;

}

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
    private Integer deptId;
    private Integer postId;
    private String username;
    private String nickname;
    private String avatar;
    private String dept;
    private String role;
    private Integer isMultipoint;
    private Integer isDisable;
    private String lastLoginIp;
    private String lastLoginTime;
    private String createTime;
    private String updateTime;

}

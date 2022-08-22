package com.mdd.admin.vo.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户Vo
 */
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String sn;
    private String avatar;
    private String realName;
    private String nickname;
    private String username;
    private String mobile;
    private String sex;
    private String channel;
    private String lastLoginIp;
    private String lastLoginTime;
    private String createTime;

}

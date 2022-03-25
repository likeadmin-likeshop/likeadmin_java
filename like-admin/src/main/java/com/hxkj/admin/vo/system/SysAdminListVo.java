package com.hxkj.admin.vo.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysAdminListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String nickname;
    private String avatar;
    private String role;
    private Boolean isDisable;
    private String lastLoginIp;
    private String lastLoginTime;
    private String createTime;
    private String updateTime;

}

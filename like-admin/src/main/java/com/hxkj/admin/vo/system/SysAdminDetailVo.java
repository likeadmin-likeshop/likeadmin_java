package com.hxkj.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysAdminDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String nickname;
    private String avatar;
    private Integer role;
    private Integer isDisable;

}

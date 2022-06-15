package com.hxkj.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统角色Vo
 */
@Data
public class SystemRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String remark;
    private Object menus;
    private Integer member;
    private Integer sort;
    private Integer isDisable;
    private String createTime;
    private String updateTime;

}

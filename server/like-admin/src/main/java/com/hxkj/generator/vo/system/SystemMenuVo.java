package com.hxkj.generator.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统菜单Vo
 */
@Data
public class SystemMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer pid;
    private String menuType;
    private String menuName;
    private String menuIcon;
    private Integer menuSort;
    private String perms;
    private String paths;
    private String component;
    private String selected;
    private String params;
    private Integer isCache;
    private Integer isShow;
    private Integer isDisable;
    private String createTime;
    private String updateTime;

}

package com.hxkj.admin.vo.system;

import lombok.Data;

@Data
public class SysMenuListVo {

    private Integer id;
    private Integer pid;
    private String menuType;
    private String menuName;
    private String menuIcon;
    private Integer menuSort;
    private String perms;
    private Boolean isDisable;
    private String createTime;
    private String updateTime;

}

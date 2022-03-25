package com.hxkj.admin.vo.system;

import lombok.Data;

@Data
public class SysRoleListVo {

    private Integer id;

    private String name;

    private String remark;

    private Integer sort;

    private Boolean isDisable;

    private String createTime;

    private String updateTime;

}

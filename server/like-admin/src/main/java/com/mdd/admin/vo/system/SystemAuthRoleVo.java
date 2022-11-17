package com.mdd.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统角色Vo
 */
@Data
public class SystemAuthRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;         // 主键
    private String name;        // 角色名称
    private String remark;      // 角色备注
    private Object menus;       // 关联菜单
    private Long member;        // 成员数量
    private Integer sort;       // 角色排序
    private Integer isDisable;  // 是否禁用: [0=否, 1=是]
    private String createTime;  // 创建时间
    private String updateTime;  // 更新时间

}

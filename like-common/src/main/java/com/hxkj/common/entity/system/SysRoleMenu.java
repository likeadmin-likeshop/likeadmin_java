package com.hxkj.common.entity.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统角色菜单实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleId;
    private Integer menuId;

}

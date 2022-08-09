package com.hxkj.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统角色菜单实体
 */
@Data
public class SystemAuthPerm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;       // 主键
    private Integer roleId;  // 角色ID
    private Integer menuId;  // 菜单ID

}

package com.hxkj.admin.service;

import com.hxkj.common.basics.BaseService;
import com.hxkj.common.entity.system.SysRoleMenu;

public interface ISysRoleMenuService extends BaseService<SysRoleMenu> {

    /**
     * 批量写入角色菜单
     *
     * @author fzr
     * @param roleId 角色ID
     * @param ids 菜单ID组
     */
    void batchSaveByMenuIds(Integer roleId, String ids);


    /**
     * 根据ID批量删除角色菜单
     *
     * @author fzr
     * @param roleId 角色ID
     */
    void batchDeleteByRoleId(Integer roleId);

}

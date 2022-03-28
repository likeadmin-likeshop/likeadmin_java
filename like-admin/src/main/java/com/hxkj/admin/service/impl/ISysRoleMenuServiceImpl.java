package com.hxkj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.hxkj.admin.service.ISysRoleMenuService;
import com.hxkj.common.entity.system.SysRoleMenu;
import com.hxkj.common.mapper.system.SysRoleMenuMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ISysRoleMenuServiceImpl extends MPJBaseServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    /**
     * 批量写入角色菜单
     *
     * @author fzr
     * @param roleId 角色ID
     * @param ids 菜单ID组
     */
    @Override
    public void batchSaveByMenuIds(Integer roleId, String ids) {
        if (ids != null && !ids.equals("")) {
            List<SysRoleMenu> arrayList = new ArrayList<>();
            for (String menuId : ids.split(",")) {
                SysRoleMenu model = new SysRoleMenu();
                model.setRoleId(roleId);
                model.setMenuId(Integer.parseInt(menuId));
                arrayList.add(model);
            }

            this.saveBatch(arrayList);
        }
    }

    /**
     * 批量删除角色菜单
     *
     * @author fzr
     * @param roleId 角色ID
     */
    @Override
    public void batchDeleteByRoleId(Integer roleId) {
        this.remove(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
    }

}

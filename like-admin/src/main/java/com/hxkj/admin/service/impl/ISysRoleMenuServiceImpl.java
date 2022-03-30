package com.hxkj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.hxkj.admin.config.SystemConfig;
import com.hxkj.admin.service.ISysMenuService;
import com.hxkj.admin.service.ISysRoleMenuService;
import com.hxkj.common.entity.system.SysMenu;
import com.hxkj.common.entity.system.SysRoleMenu;
import com.hxkj.common.mapper.system.SysRoleMenuMapper;
import com.hxkj.common.utils.RedisUtil;
import com.hxkj.common.utils.ToolsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ISysRoleMenuServiceImpl extends MPJBaseServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Resource
    ISysMenuService iSysMenuService;

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

    /**
     * 缓存角色菜单
     *
     * @author fzr
     * @param roleId 角色ID
     */
    @Override
    public void cacheRoleMenusByRoleId(Integer roleId) {
        List<Integer> menuIds  = new LinkedList<>();
        List<String> menuArray = new LinkedList<>();

        List<SysRoleMenu> sysRoleMenus = this.list(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
        for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
            menuIds.add(sysRoleMenu.getMenuId());
        }

        if (menuIds.size() > 0) {
            List<SysMenu> sysMenus = iSysMenuService.list(new QueryWrapper<SysMenu>()
                    .select("id,perms")
                    .in("id", menuIds)
                    .eq("is_disable", 0));

            for (SysMenu sysMenu : sysMenus) {
                menuArray.add(sysMenu.getPerms().trim());
            }
        }

        RedisUtil.hSet(SystemConfig.backstageRolesKey, String.valueOf(roleId), ToolsUtil.listStrToString(menuArray, ","));
    }

}

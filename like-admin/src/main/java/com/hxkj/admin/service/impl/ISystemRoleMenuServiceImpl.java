package com.hxkj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.hxkj.admin.config.SystemConfig;
import com.hxkj.admin.service.ISystemMenuService;
import com.hxkj.admin.service.ISystemRoleMenuService;
import com.hxkj.common.entity.system.SystemMenu;
import com.hxkj.common.entity.system.SystemRoleMenu;
import com.hxkj.common.mapper.system.SystemRoleMenuMapper;
import com.hxkj.common.utils.RedisUtil;
import com.hxkj.common.utils.ToolsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ISystemRoleMenuServiceImpl extends MPJBaseServiceImpl<SystemRoleMenuMapper, SystemRoleMenu> implements ISystemRoleMenuService {

    @Resource
    ISystemMenuService iSystemMenuService;

    /**
     * 根据角色ID获取菜单ID
     *
     * @param roleId 角色ID
     * @return List<Integer>
     */
    @Override
    public List<Integer> selectMenuIdsByRoleId(Integer roleId) {
        List<Integer> menus = new LinkedList<>();
        List<SystemRoleMenu> systemRoleMenus = this.list(new QueryWrapper<SystemRoleMenu>().eq("role_id", roleId));
        for (SystemRoleMenu systemRoleMenu : systemRoleMenus) {
            menus.add(systemRoleMenu.getMenuId());
        }
        return menus;
    }

    /**
     * 批量写入角色菜单
     *
     * @author fzr
     * @param roleId 角色ID
     * @param menuIds 菜单ID组
     */
    @Override
    public void batchSaveByMenuIds(Integer roleId, String menuIds) {
        if (menuIds != null && !menuIds.equals("")) {
            List<SystemRoleMenu> arrayList = new ArrayList<>();
            for (String menuId : menuIds.split(",")) {
                SystemRoleMenu model = new SystemRoleMenu();
                model.setRoleId(roleId);
                model.setMenuId(Integer.parseInt(menuId));
                arrayList.add(model);
            }
            this.saveBatch(arrayList);
        }
    }

    /**
     * 批量删除角色菜单(根据角色ID)
     *
     * @author fzr
     * @param roleId 角色ID
     */
    @Override
    public void batchDeleteByRoleId(Integer roleId) {
        this.remove(new QueryWrapper<SystemRoleMenu>().eq("role_id", roleId));
    }

    /**
     * 批量删除角色菜单(根据菜单ID)
     *
     * @author fzr
     * @param menuId 菜单ID
     */
    @Override
    public void batchDeleteByMenuId(Integer menuId) {
        this.remove(new QueryWrapper<SystemRoleMenu>().eq("menu_id", menuId));
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

        List<SystemRoleMenu> systemRoleMenus = this.list(new QueryWrapper<SystemRoleMenu>().eq("role_id", roleId));
        for (SystemRoleMenu systemRoleMenu : systemRoleMenus) {
            menuIds.add(systemRoleMenu.getMenuId());
        }

        if (menuIds.size() > 0) {
            List<SystemMenu> systemMenus = iSystemMenuService.list(new QueryWrapper<SystemMenu>()
                    .select("id,perms")
                    .in("id", menuIds)
                    .eq("is_disable", 0));

            for (SystemMenu systemMenu : systemMenus) {
                menuArray.add(systemMenu.getPerms().trim());
            }
        }

        RedisUtil.hSet(SystemConfig.backstageRolesKey, String.valueOf(roleId), ToolsUtil.listStrToString(menuArray, ","));
    }

}

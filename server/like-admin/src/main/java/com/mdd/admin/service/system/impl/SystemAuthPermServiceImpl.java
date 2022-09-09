package com.mdd.admin.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.config.AdminConfig;
import com.mdd.admin.service.system.ISystemAuthPermService;
import com.mdd.common.entity.system.SystemAuthMenu;
import com.mdd.common.entity.system.SystemAuthPerm;
import com.mdd.common.mapper.system.SystemAuthMenuMapper;
import com.mdd.common.mapper.system.SystemAuthPermMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.RedisUtil;
import com.mdd.common.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 系统权限服务实现类
 */
@Service
public class SystemAuthPermServiceImpl implements ISystemAuthPermService {

    @Resource
    SystemAuthPermMapper systemAuthPermMapper;

    @Resource
    SystemAuthMenuMapper systemAuthMenuMapper;

    /**
     * 根据角色ID获取菜单ID
     *
     * @param roleId 角色ID
     * @return List<Integer>
     */
    @Override
    public List<Integer> selectMenuIdsByRoleId(Integer roleId) {
        List<Integer> menus = new LinkedList<>();
        List<SystemAuthPerm> systemAuthPerms = systemAuthPermMapper.selectList(
                new QueryWrapper<SystemAuthPerm>().eq("role_id", roleId));
        for (SystemAuthPerm systemAuthPerm : systemAuthPerms) {
            menus.add(systemAuthPerm.getMenuId());
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
    @Transactional
    public void batchSaveByMenuIds(Integer roleId, String menuIds) {
        if (menuIds != null && !menuIds.equals("")) {
            for (String menuId : menuIds.split(",")) {
                SystemAuthPerm model = new SystemAuthPerm();
                model.setRoleId(roleId);
                model.setMenuId(Integer.parseInt(menuId));
                systemAuthPermMapper.insert(model);
            }
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
        systemAuthPermMapper.delete(new QueryWrapper<SystemAuthPerm>().eq("role_id", roleId));
    }

    /**
     * 批量删除角色菜单(根据菜单ID)
     *
     * @author fzr
     * @param menuId 菜单ID
     */
    @Override
    public void batchDeleteByMenuId(Integer menuId) {
        systemAuthPermMapper.delete(new QueryWrapper<SystemAuthPerm>().eq("menu_id", menuId));
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

        List<SystemAuthPerm> systemAuthPerms = systemAuthPermMapper.selectList(
                new QueryWrapper<SystemAuthPerm>().eq("role_id", roleId));
        for (SystemAuthPerm systemAuthPerm : systemAuthPerms) {
            menuIds.add(systemAuthPerm.getMenuId());
        }

        if (menuIds.size() > 0) {
            List<SystemAuthMenu> systemAuthMenus = systemAuthMenuMapper.selectList(new QueryWrapper<SystemAuthMenu>()
                    .select("id,perms")
                    .eq("is_disable", 0)
                    .in("id", menuIds)
                    .in("menu_type", Arrays.asList("C", "A"))
                    .orderByAsc(Arrays.asList("menu_sort", "id")));

            for (SystemAuthMenu item : systemAuthMenus) {
                if (StringUtil.isNotNull(item.getPerms()) && StringUtil.isNotEmpty(item.getPerms())) {
                    menuArray.add(item.getPerms().trim());
                }
            }
        }

        RedisUtil.hSet(AdminConfig.backstageRolesKey, String.valueOf(roleId), ArrayUtil.listToStringByStr(menuArray, ","));
    }

}

package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.service.ISystemAuthPermService;
import com.mdd.common.entity.system.SystemAuthPerm;
import com.mdd.common.entity.system.SystemAuthRole;
import com.mdd.common.mapper.system.SystemAuthPermMapper;
import com.mdd.common.mapper.system.SystemAuthRoleMapper;
import com.mdd.common.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    SystemAuthRoleMapper systemAuthRoleMapper;

    /**
     * 根据角色ID获取菜单ID
     *
     * @param roleIds 角色ID
     * @return List<Integer>
     */
    @Override
    public List<Integer> selectMenuIdsByRoleId(List<Integer> roleIds) {
        List<Integer> menus = new LinkedList<>();

        if (roleIds.isEmpty()) {
            return menus;
        }

        SystemAuthRole systemAuthRole = systemAuthRoleMapper.selectOne(new QueryWrapper<SystemAuthRole>()
                .in("id", roleIds)
                .eq("is_disable", 0)
                .last("limit 1"));

        if (StringUtils.isNull(systemAuthRole)) {
            return menus;
        }

        List<SystemAuthPerm> systemAuthPerms = systemAuthPermMapper.selectList(
                new QueryWrapper<SystemAuthPerm>()
                        .in("role_id", roleIds));

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

}

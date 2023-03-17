package com.mdd.admin.config.stp;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.common.entity.system.SystemAuthMenu;
import com.mdd.common.entity.system.SystemAuthPerm;
import com.mdd.common.mapper.system.SystemAuthMenuMapper;
import com.mdd.common.mapper.system.SystemAuthPermMapper;
import com.mdd.common.util.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Sa-Token自定义权限验证接口
 */
@Component
public class StpInterConfig implements StpInterface {

    @Resource
    SystemAuthPermMapper systemAuthPermMapper;

    @Resource
    SystemAuthMenuMapper systemAuthMenuMapper;

    /**
     * 返回一个账号所拥有的权限码集合
     *
     * @param loginId 登录ID
     * @param loginType 登录类型
     * @return List<String>
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<Integer> roleIds  = LikeAdminThreadLocal.getRoleIds();
        List<String> perms = new LinkedList<>();

        if (roleIds.isEmpty()) {
            return perms;
        }

        List<SystemAuthPerm> permList = systemAuthPermMapper.selectList(
                new QueryWrapper<SystemAuthPerm>()
                        .select("id,role_id,menu_id")
                        .in("role_id", roleIds));

        if (permList.isEmpty()) {
            return perms;
        }

        List<Integer> menuIds = new LinkedList<>();
        for (SystemAuthPerm systemAuthPerm : permList) {
            menuIds.add(systemAuthPerm.getMenuId());
        }

        List<SystemAuthMenu> systemAuthMenus = systemAuthMenuMapper.selectList(
                new QueryWrapper<SystemAuthMenu>()
                        .select("id,perms")
                        .eq("is_disable", 0)
                        .in("id", menuIds)
                        .in("menu_type", Arrays.asList("C", "A"))
                        .orderByAsc(Arrays.asList("menu_sort", "id")));

        for (SystemAuthMenu item : systemAuthMenus) {
            if (StringUtils.isNotNull(item.getPerms()) && StringUtils.isNotEmpty(item.getPerms())) {
                perms.add(item.getPerms().trim());
            }
        }

        return perms;
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     *
     * @param loginId 登录ID
     * @param loginType 登录类型
     * @return List<String>
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }

}

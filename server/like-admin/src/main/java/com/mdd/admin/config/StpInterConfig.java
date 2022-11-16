package com.mdd.admin.config;

import cn.dev33.satoken.stp.StpInterface;
import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.admin.service.ISystemAuthPermService;
import com.mdd.common.utils.RedisUtil;
import com.mdd.common.utils.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Sa-Token自定义权限验证接口
 */
@Component
public class StpInterConfig implements StpInterface {

    @Resource
    ISystemAuthPermService iSystemAuthPermService;

    /**
     * 返回一个账号所拥有的权限码集合
     *
     * @param loginId 登录ID
     * @param loginType 登录类型
     * @return List<String>
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Integer roleId  = LikeAdminThreadLocal.getRoleId();
        Object menusObj = RedisUtil.hGet(AdminConfig.backstageRolesKey, String.valueOf(roleId));
        if (StringUtil.isNull(menusObj)) {
            iSystemAuthPermService.cacheRoleMenusByRoleId(roleId);
            menusObj = RedisUtil.hGet(AdminConfig.backstageRolesKey, String.valueOf(roleId));
        }

        List<String> list = new ArrayList<>();
        String[] menus = menusObj.toString().split(",");
        for (String auth : menus) {
            list.add(auth.toLowerCase());
        }

        return list;
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

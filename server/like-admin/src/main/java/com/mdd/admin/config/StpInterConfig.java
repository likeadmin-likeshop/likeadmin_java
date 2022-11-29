package com.mdd.admin.config;

import cn.dev33.satoken.stp.StpInterface;
import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.admin.service.ISystemAuthPermService;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.RedisUtil;
import com.mdd.common.utils.StringUtil;
import com.mdd.common.utils.ToolsUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

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
        List<Integer> roleIds  = LikeAdminThreadLocal.getRoleId();
        List<String> perms = new LinkedList<>();

        for (Integer roleId : roleIds) {
            Object menusObj = RedisUtil.hGet(AdminConfig.backstageRolesKey, String.valueOf(roleId));
            if (StringUtil.isNull(menusObj)) {
                iSystemAuthPermService.cacheRoleMenusByRoleId(roleId);
                menusObj = RedisUtil.hGet(AdminConfig.backstageRolesKey, String.valueOf(roleId));
            }
            if (StringUtil.isNotNull(menusObj)) {
                perms.addAll(ArrayUtil.stringToListAsStr(menusObj.toString(), ","));
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

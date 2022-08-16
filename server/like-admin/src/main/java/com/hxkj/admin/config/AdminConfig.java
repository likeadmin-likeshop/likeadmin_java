package com.hxkj.admin.config;

/**
 * 后台公共配置
 */
public class AdminConfig {

    // 管理缓存键
    public static final String backstageManageKey = "backstage:manage";

    // 角色缓存键
    public static final String backstageRolesKey = "backstage:roles";

    // 令牌缓存键
    public static final String backstageTokenKey = "backstage:token:";

    // 免登录验证
    public static String[] notLoginUri = new String[]{
            "system:login",         // 登录接口
            "common:index:config",  // 配置接口
            "gen:downloadCode",  // 配置接口
    };

    // 免权限验证
    public static String[] notAuthUri = new String[]{
            "system:logout",         // 退出登录
            "system:menu:menus",     // 系统菜单
            "system:menu:route",     // 菜单路由
            "system:admin:upInfo",   // 管理员更新
            "system:admin:self",     // 管理员信息
            "system:role:all",       // 所有角色
            "system:post:all",       // 所有岗位
            "system:dept:list",      // 所有部门
            "setting:dict:type:all", // 所有字典类型
            "setting:dict:data:all", // 所有字典数据
    };

}

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
            "/api/system/login",
            "/api/index/config",
    };

    // 免权限验证
    public static String[] notAuthUri = new String[]{};

}

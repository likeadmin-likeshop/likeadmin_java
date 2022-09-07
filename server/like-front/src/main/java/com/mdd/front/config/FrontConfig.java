package com.mdd.front.config;

/**
 * 前台公共配置
 */
public class FrontConfig {

    // 登录缓存键
    public static final String frontendTokenKey = "frontend:token:";

    // 免登录验证
    public static String[] notLoginUri = new String[]{
            "/api/login",
            "/api/index",
            "/api/config",
            "/api/policy",
            "/api/search",
            "/api/decorate",
            "/api/sms/send",

            "/api/login/check",
            "/api/login/register",
            "/api/login/forgotPassword",

            "/api/article/category",
            "/api/article/detail",
            "/api/article/list",
    };

}

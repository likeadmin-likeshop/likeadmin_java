package com.mdd.front.config;

/**
 * 前台公共配置
 */
public class FrontConfig {

    // 登录缓存键
    public static final String frontendTokenKey = "frontend:token:";

    // 免登录验证
    public static String[] notLoginUri = new String[]{
            "/api/index",
            "/api/config",
            "/api/policy",
            "/api/search",
            "/api/hotSearch",
            "/api/decorate",
            "/api/sms/send",
            "/api/upload/image",

            "/api/login/check",
            "/api/login/codeUrl",
            "/api/login/register",
            "/api/login/forgotPassword",

            "/api/article/category",
            "/api/article/detail",
            "/api/article/list",
    };

}

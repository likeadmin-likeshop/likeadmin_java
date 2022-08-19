package com.mdd.front.config;

/**
 * 前台公共配置
 */
public class FrontConfig {

    // 免登录验证
    public static String[] notLoginUri = new String[]{
            "/api/login"
    };

    // 免权限验证
    public static String[] notAuthUri = new String[]{};

}

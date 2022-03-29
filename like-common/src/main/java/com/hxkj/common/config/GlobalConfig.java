package com.hxkj.common.config;

/**
 * 全局配置
 */
public class GlobalConfig {

    // 开启调试模式
    public static Boolean debug = true;

    // 当前代码版本
    public static String version = "v1.1.0";

    // 系统加密字符
    public static String secret = "UVTIyzCy";

    // Redis键前缀
    public static String redisPrefix = "Like:";

    // 资源访问前缀
    public static String publicPrefix = "uploads";

}

package com.hxkj.common.config;

/**
 * 全局配置
 */
public class GlobalConfig {

    // 开启调试模式
    public static Boolean debug = true;

    // 获取地址开关
    public static Boolean isAddressEnabled = false;

    // 当前代码版本
    public static String version = "v1.1.0";

    // 系统加密字符
    public static String secret = "UVTIyzCy";

    // Redis键前缀
    public static String redisPrefix = "Like:";

    // 资源访问前缀
    public static String publicPrefix = "uploads";

    // 上传映射目录
    public static String uploadDirectory = "/www/wwwroot/uploads/";

    // 上传图片限制
    public static Integer uploadImageSize = 1024 * 1024 * 10;

    // 上传视频限制
    public static Integer uploadVideoSize = 1024 * 1024 * 10;

    // 上传图片扩展
    public static String[] uploadImageExt = new String[] {"png", "jpg", "jpeg", "gif", "ico", "bmp"};

    // 上传视频扩展
    public static String[] uploadVideoExt = new String[] {"mp4", "mp3", "avi", "flv", "rmvb", "mov"};

}

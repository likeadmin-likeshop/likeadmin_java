package com.hxkj.common.utils;

import com.hxkj.common.config.GlobalConfig;

import java.util.Map;

/**
 * 文件路径处理工具
 */
public class UrlUtil {

    /**
     * 访问前缀
     */
    private static final String uploadPrefix = GlobalConfig.publicPrefix;

    /**
     * 转绝对路径
     * 转前: uploads/11.png
     * 转后: https://127.0.0.1/uploads/11.png
     *
     * @author fzr
     * @param url 相对路径
     * @return String
     */
    public static String toAbsoluteUrl(String url) {
        if (url == null || url.equals("")) {
            return "";
        }

        if(url.indexOf("/") != 0) {
            url = "/" + url;
        }

        String engine = ConfigUtil.get("storage", "default", "local");
        engine = engine.equals("") ? "local" : engine;
        if (engine.equals("local")) {
            return RequestUtil.uri() + "/" + uploadPrefix + url;
        }

        Map<String, String> config = ConfigUtil.getMap("storage", engine);
        return config.getOrDefault("domain", "") + url;
    }

    /**
     * 转相对路径
     * 转前: https://127.0.0.1/uploads/11.png
     * 转后: uploads/11.png
     *
     * @author fzr
     * @param url 绝对路径
     * @return String
     */
    public static String toRelativeUrl(String url) {
        if (url == null || url.equals("")) {
            return "";
        }

        String engine = ConfigUtil.get("storage", "default", "local");
        engine = engine.equals("") ? "local" : engine;
        if (engine.equals("local")) {
            return url.replace(RequestUtil.uri() + "/" + uploadPrefix + "/", "");
        }

        Map<String, String> config = ConfigUtil.getMap("storage", engine);
        return url.replace(config.getOrDefault("domain", "") + "/" + uploadPrefix + "/", "");
    }

    /**
     * 获取存储域名
     * 示例: https://127.0.0.1/
     *
     * @author fzr
     * @return String
     */
    public static String domain() {
        String engine = ConfigUtil.get("storage", "default", "local");
        engine = engine.equals("") ? "local" : engine;
        if (engine.equals("local")) {
            return RequestUtil.uri() + "/";
        }

        Map<String, String> config = ConfigUtil.getMap("storage", engine);
        return config.getOrDefault("domain", "") + "/";
    }

}

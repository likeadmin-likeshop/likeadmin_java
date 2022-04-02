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
        if (engine.equals("local")) {
            return HttpUtil.domain() + "/" + uploadPrefix + url;
        }

        Map<String, String> config = ConfigUtil.getMap("storage", engine);
        return config.get("domain") + url;
    }

    /**
     * 转相对路径
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
        if (engine.equals("local")) {
            return url.replace(HttpUtil.domain() + "/" + uploadPrefix + "/", "");
        }

        Map<String, String> config = ConfigUtil.getMap("storage", engine);
        return url.replace(config.get("domain") + "/" + uploadPrefix + "/", "");
    }

}

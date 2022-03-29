package com.hxkj.common.utils;

/**
 * 文件路径处理工具
 */
public class UrlUtil {

    /**
     * 访问前缀
     */
    private static final String uploadPrefix = "upload";

    /**
     * 转绝对路径
     *
     * @author fzr
     * @param url 相对路径
     * @return String
     */
    public static String toAbsoluteUrl(String url) {
        if(url.indexOf("/")!=0) {
            url = "/" + url;
        }

        return HttpUtil.domain() + "/" + uploadPrefix + url;
    }

    /**
     * 转相对路径
     *
     * @param url 绝对路径
     * @return String
     */
    public static String toRelativeUrl(String url) {
        if (url == null || url.equals("")) {
            return "";
        }

        return url.replace(HttpUtil.domain() + "/" + uploadPrefix + "/", "");
    }

}

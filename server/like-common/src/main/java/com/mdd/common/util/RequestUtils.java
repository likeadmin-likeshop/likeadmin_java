package com.mdd.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 请求工具类
 */
public class RequestUtils {

    /**
     * 获取请求对象
     *
     * @author fzr
     * @return HttpServletRequest
     */
    public static HttpServletRequest handler() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 获取不带参请求URl
     * 示例: https://127.0.0.1:8082/api/system/menu/menus
     *
     * @author fzr
     * @return String
     */
    public static String url() {
        HttpServletRequest request = RequestUtils.handler();
        if (request != null) {
            return request.getRequestURL().toString();
        }
        return "";
    }

    /**
     * 获取带端口的请求地址
     * 示例: https://127.0.0.1:8082
     *
     * @author fzr
     * @return String
     */
    public static String uri() {
        String domain = RequestUtils.domain();
        if (!Arrays.asList(443,80,0).contains(RequestUtils.port())) {
            domain += ":" + RequestUtils.port();
        }

        return domain;
    }

    /**
     * 获取请求路由
     * 示例: /api/system/menu/menus
     *
     * @author fzr
     * @return String
     */
    public static String route() {
        HttpServletRequest request = RequestUtils.handler();
        if (request != null) {
            return request.getRequestURI();
        }
        return "";
    }

    /**
     * 获取请求端口
     * 示例: 443/80
     *
     * @author fzr
     * @return Integer
     */
    public static Integer port() {
        HttpServletRequest request = RequestUtils.handler();
        if (request != null) {
            return request.getServerPort();
        }
        return 0;
    }

    /**
     * 获取请求域名
     * 示例: https://127.0.0.1
     *
     * @author fzr
     * @return String
     */
    public static String domain() {
        HttpServletRequest request = RequestUtils.handler();
        if (request != null) {
            String requestUrl = request.getRequestURL().toString();
            List<String> urls = Arrays.asList(requestUrl.split("/"));

            String agree = "http:";
            if (request.getServerPort() == 443) {
                agree = "https:";
            }

            return agree + "//" + urls.get(2).split(":")[0];
        }
        return null;
    }

    /**
     * 获取设备标识
     *
     * @author fzr
     * @return String
     */
    public static String device() {
        HttpServletRequest request = RequestUtils.handler();
        if (request != null) {
            String userAgent = request.getHeader("User-Agent");
            if(userAgent.contains("Android")) {
                return "Android";
            }else if(userAgent.contains("iPhone") || userAgent.contains("iPod") || userAgent.contains("iPad")) {
                return "IOS";
            } else {
                return "wap";
            }
        }

        return "wap";
    }

    /**
     * 判断是否是GET请求
     *
     * @author fzr
     * @return Boolean
     */
    public static Boolean isGet() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return request.getMethod().equals("GET");
        }
        return false;
    }

    /**
     * 判断是否是POST请求
     *
     * @author fzr
     * @return Boolean
     */
    public static Boolean isPost() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return request.getMethod().equals("POST");
        }
        return false;
    }

    /**
     * 判断是否是PUT请求
     *
     * @author fzr
     * @return Boolean
     */
    public static Boolean isPUT() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return request.getMethod().equals("PUT");
        }
        return false;
    }

    /**
     * 判断是否是DELETE请求
     *
     * @author fzr
     * @return Boolean
     */
    public static Boolean isDelete() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return request.getMethod().equals("DELETE");
        }
        return false;
    }

}

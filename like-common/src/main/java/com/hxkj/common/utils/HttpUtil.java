package com.hxkj.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * 请求工具类
 */
public class HttpUtil {

    /**
     * 获取请求对象
     *
     * @author fzr
     * @return HttpServletRequest
     */
    public static HttpServletRequest obj() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 获取不带参请求URl
     *
     * @author fzr
     * @return String
     */
    public static String url() {
        HttpServletRequest request = HttpUtil.obj();
        if (request != null) {
            return request.getRequestURL().toString();
        }
        return null;
    }

    /**
     * 获取请求域名
     *
     * @author fzr
     * @return String
     */
    public static String domain() {
        HttpServletRequest request = HttpUtil.obj();
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
     * 请求客户端IP
     *
     * @author fzr
     * @return String
     */
    public static String ip() {
        HttpServletRequest request = HttpUtil.obj();
        if (request != null) {
            String ipAddress;
            try {
                ipAddress = request.getHeader("x-forwarded-for");
                if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                    ipAddress = request.getHeader("Proxy-Client-IP");
                }
                if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                    ipAddress = request.getHeader("WL-Proxy-Client-IP");
                }
                if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                    ipAddress = request.getRemoteAddr();
                    if (ipAddress.equals("127.0.0.1")) {
                        // 根据网卡取本机配置的IP
                        InetAddress inet = null;
                        try {
                            inet = InetAddress.getLocalHost();
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                        assert inet != null;
                        ipAddress = inet.getHostAddress();
                    }
                }
                // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
                // "***.***.***.***".length()
                if (ipAddress != null && ipAddress.length() > 15) {
                    // = 15
                    if (ipAddress.indexOf(",") > 0) {
                        ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                    }
                }
            } catch (Exception e) {
                ipAddress="";
            }
            return ipAddress == null ? "" : ipAddress;
        }
        return "";
    }


}

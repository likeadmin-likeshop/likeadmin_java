package com.mdd.front;

import java.util.LinkedHashMap;
import java.util.Map;

public class LikeFrontThreadLocal {

    /**
     * 构造方法
     */
    public LikeFrontThreadLocal() {}

    /**
     * 取得本地线程对象
     */
    private static final java.lang.ThreadLocal<LinkedHashMap<String, Object>> MY_LOCAL = new java.lang.ThreadLocal<>();

    /**
     * 写入本地线程
     */
    public static void put(String key, Object val) {
        LinkedHashMap<String, Object> map = MY_LOCAL.get();
        if (map == null) {
            map = new LinkedHashMap<>();
        }

        map.put(key, val);
        MY_LOCAL.set(map);
    }

    /**
     * 获取本地线程
     */
    public static Object get(String key) {
        Map<String, Object> map = MY_LOCAL.get();
        if (map == null) {
            return null;
        }
        return map.getOrDefault(key, "");
    }

    /**
     * 获取用户ID
     */
    public static Integer getUserId() {
        Object adminId = LikeFrontThreadLocal.get("userId");
        if (adminId == null || adminId.toString().equals("")) {
            return 0;
        }
        return Integer.parseInt(adminId.toString());
    }

    /**
     * 删除本地线程
     */
    public static void remove() {
        MY_LOCAL.remove();
    }

}

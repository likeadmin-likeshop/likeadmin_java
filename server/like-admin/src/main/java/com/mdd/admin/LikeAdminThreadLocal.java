package com.mdd.admin;

import com.mdd.common.utils.ArrayUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 本地线程
 */
public class LikeAdminThreadLocal {

    /**
     * 构造方法
     */
    public LikeAdminThreadLocal() {}

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
        return MY_LOCAL.get().getOrDefault(key, "");
    }

    /**
     * 获取管理员ID
     */
    public static Integer getAdminId() {
        String adminId = LikeAdminThreadLocal.get("adminId").toString();
        if (adminId.equals("")) {
            return 0;
        }
        return Integer.parseInt(adminId);
    }

    /**
     * 获取角色ID
     */
    public static List<Integer> getRoleId() {
        String roleIds = LikeAdminThreadLocal.get("roleIds").toString();
        if (roleIds.equals("") || roleIds.equals("0")) {
            return Collections.emptyList();
        }
        return ArrayUtil.stringToListAsInt(roleIds, ",");
    }

    /**
     * 删除本地线程
     */
    public static void remove() {
        MY_LOCAL.remove();
    }

}

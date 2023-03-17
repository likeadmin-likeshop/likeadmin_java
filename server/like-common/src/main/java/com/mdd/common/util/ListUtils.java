package com.mdd.common.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

/**
 * 列表工具类
 */
public class ListUtils {

    /**
     * JSONArray转树形结构
     *
     * @author fzr
     * @param arr JSON数组
     * @param id 主键字段名
     * @param pid 上级字段名
     * @param child 子级字段名
     * @return JSONArray
     */
    public static JSONArray listToTree(JSONArray arr, String id, String pid, String child) {
        JSONArray r = new JSONArray();
        JSONObject hash = new JSONObject();
        //将数组转为Object的形式,key为数组中的id
        for (Object o : arr) {
            JSONObject json = (JSONObject) o;
            hash.put(json.getString(id), json);
        }
        //遍历结果集
        for (Object o : arr) {
            //单条记录
            JSONObject aVal = (JSONObject) o;

            //在hash中取出key为单条记录中pid的值
            JSONObject hashVP = (JSONObject) hash.get(aVal.get(pid).toString());
            //如果记录的pid存在,则说明它有父节点,将她添加到孩子节点的集合中
            if (hashVP != null) {
                //检查是否有child属性
                if (hashVP.get(child) != null) {
                    JSONArray ch = (JSONArray) hashVP.get(child);
                    ch.add(aVal);
                    hashVP.put(child, ch);
                } else {
                    JSONArray ch = new JSONArray();
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }
            } else {
                r.add(aVal);
            }
        }

        return r;
    }

    /**
     * List去重,不打乱原来顺序,泛型list对象
     * 对象重写hashCode和equals
     *
     * @author fzr
     * @param <T> 泛型
     * @param list 列表
     * @return List
     */
    public static <T> List<T> uniqueBySetOrder(List<T> list){
        Set<T> set = new HashSet<T>();
        List<T> newList = new ArrayList<T>();
        for(T t: list){
            if(set.add(t)){
                newList.add(t);
            }
        }
        return newList;
    }

    /**
     * List去重,可能打乱原来顺序,泛型list对象
     * 对象重写hashCode和equals
     *
     * @author fzr
     * @param list 列表
     * @return List
     */
    public static <T> List<T> uniqueBySet(List<T> list){
        return new ArrayList<T>(new HashSet<T>(list));
    }

    /**
     * 列表转字符串
     *
     * @author fzr
     * @param list 列表 [1, 2, 4] -> 1,2,3
     * @param separator 分割符号
     * @return String
     */
    public static String listToStringByLong(List<Long> list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (Object o : list) {
            sb.append(o).append(separator);
        }
        return list.isEmpty() ? "" : sb.substring(0, sb.toString().length() - 1);
    }

    /**
     * 列表转字符串
     *
     * @author fzr
     * @param list 列表 [1, 2, 4] -> 1,2,3
     * @param separator 分割符号
     * @return String
     */
    public static String listToStringByInt(List<Integer> list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (Object o : list) {
            sb.append(o).append(separator);
        }
        return list.isEmpty() ? "" : sb.substring(0, sb.toString().length() - 1);
    }

    /**
     * 列表转字符串
     *
     * @author fzr
     * @param list 列表 ["1", "2", "3"] -> 1,2,3
     * @param separator 分割符号
     * @return String
     */
    public static String listToStringByStr(List<String> list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (Object o : list) {
            sb.append(o).append(separator);
        }
        return list.isEmpty() ? "" : sb.substring(0, sb.toString().length() - 1);
    }

    /**
     * 字符串分割,转化为数组
     *
     * @author fzr
     * @param str 字符串
     * @param regex 分隔符
     * @return List<Long>
     */
    public static List<Long> stringToListAsLong(String str, String regex){
        List<Long> list = new ArrayList<>();
        if (str.contains(regex)){
            String[] split = str.split(regex);
            for (String value : split) {
                if(!StringUtils.isBlank(value)){
                    list.add(Long.parseLong(value.trim()));
                }
            }
        }else {
            list.add(Long.parseLong(str));
        }
        return list;
    }

    /**
     * 字符串分割,转化为数组
     *
     * @author fzr
     * @param str 字符串
     * @param regex 分隔符
     * @return List<Integer>
     */
    public static List<Integer> stringToListAsInt(String str, String regex){
        List<Integer> list = new ArrayList<>();
        if (str.equals("")) {
            return list;
        }
        if (str.contains(regex)){
            String[] split = str.split(regex);
            for (String value : split) {
                if(!StringUtils.isBlank(value)){
                    list.add(Integer.parseInt(value.trim()));
                }
            }
        }else {
            list.add(Integer.parseInt(str));
        }
        return list;
    }

    /**
     * 字符串分割,转化为数组
     *
     * @author fzr
     * @param str 字符串
     * @param regex 分隔符
     * @return List<String>
     */
    public static List<String> stringToListAsStr(String str, String regex){
        List<String> list = new ArrayList<>();
        if (str.contains(regex)){
            String[] split = str.split(regex);
            for (String value : split) {
                if(!StringUtils.isBlank(value)){
                    list.add(value.trim());
                }
            }
        }else {
            list.add(str);
        }
        return list;
    }

    /**
     * 字符串转列表Map
     *
     * @author fzr
     * @param s 字符串
     * @return Map<String, Long>
     */
    public static List<Map<String, Long>> stringToListAsMapLong(String s) {
        Type type = new TypeToken<List<Map<String, Long>>>() {}.getType();
        return JSON.parseObject(s, type);
    }

    /**
     * 字符串转列表Map
     *
     * @author fzr
     * @param s 字符串
     * @return Map<String, Integer>
     */
    public static List<Map<String, Integer>> stringToListAsMapInt(String s) {
        Type type = new TypeToken<List<Map<String, Integer>>>() {}.getType();
        return JSON.parseObject(s, type);
    }

    /**
     * 字符串转列表Map
     *
     * @author fzr
     * @param s 字符串
     * @return Map<String, Object>
     */
    public static List<Map<String, Object>> stringToListAsMapObj(String s) {
        if (StringUtils.isEmpty(s)) {
            return Collections.emptyList();
        }
        Type type = new TypeToken<List<Map<String, Object>>>() {}.getType();
        return JSON.parseObject(s, type);
    }

    /**
     * 字符串转列表Map
     *
     * @author fzr
     * @param s 字符串
     * @return Map<String, String>
     */
    public static List<Map<String, String>> stringToListAsMapStr(String s) {
        if (StringUtils.isEmpty(s)) {
            return Collections.emptyList();
        }
        Type type = new TypeToken<List<Map<String, String>>>() {}.getType();
        return JSON.parseObject(s, type);
    }

    /**
     * 对象转List
     *
     * @author fzr
     * @param object 对象
     * @return List<Long>
     */
    public static List<Integer> objectToListAsLong(Object object) {
        if (StringUtils.isNull(object)) {
            return Collections.emptyList();
        }
        Type type = new TypeToken<List<Long>>() {}.getType();
        return JSON.parseObject(JSONObject.toJSONString(object), type);
    }

    /**
     * 对象转List
     *
     * @author fzr
     * @param object 对象
     * @return List<Integer>
     */
    public static List<Integer> objectToListAsInt(Object object) {
        if (StringUtils.isNull(object)) {
            return Collections.emptyList();
        }
        Type type = new TypeToken<List<Integer>>() {}.getType();
        return JSON.parseObject(JSONObject.toJSONString(object), type);
    }

    /**
     * 对象转List
     *
     * @author fzr
     * @param object 对象
     * @return List<String>
     */
    public static List<String> objectToListAsStr(Object object) {
        if (StringUtils.isNull(object)) {
            return Collections.emptyList();
        }
        Type type = new TypeToken<List<String>>() {}.getType();
        return JSON.parseObject(JSONObject.toJSONString(object), type);
    }

}

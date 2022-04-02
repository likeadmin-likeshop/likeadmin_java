package com.hxkj.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 数组工具类
 */
public class ArrayUtil {

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
    public static String listToString(List<Long> list, String separator) {
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
    public static String listStrToString(List<String> list, String separator) {
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
     * @return List<Integer>
     */
    public static List<Integer> stringToArray(String str){
        return stringToArrayByRegex(str, ",");
    }

    /**
     * 字符串分割，转化为数组
     *
     * @author fzr
     * @param str 字符串
     * @return List<String>
     */
    public static List<String> stringToArrayStr(String str){
        return stringToArrayStrRegex(str, ",");
    }

    /**
     * 字符串分割,转化为数组
     *
     * @author fzr
     * @param str 字符串
     * @param regex 分隔符
     * @return List<Integer>
     */
    public static List<Integer> stringToArrayByRegex(String str, String regex){
        List<Integer> list = new ArrayList<>();
        if (str.contains(regex)){

            String[] split = str.split(regex);

            for (String value : split) {
                if(!StringUtil.isBlank(value)){
                    list.add(Integer.parseInt(value.trim()));
                }
            }
        }else {
            list.add(Integer.parseInt(str));
        }
        return list;
    }

    /**
     * 字符串分割，转化为数组
     *
     * @author fzr
     * @param str 字符串
     * @param regex 分隔符
     * @return List<String>
     */
    public static List<String> stringToArrayStrRegex(String str, String regex ){
        List<String> list = new ArrayList<>();
        if (str.contains(regex)){

            String[] split = str.split(regex);

            for (String value : split) {
                if(!StringUtil.isBlank(value)){
                    list.add(value);
                }
            }
        }else {
            list.add(str);
        }
        return list;
    }

}

package com.hxkj.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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


}

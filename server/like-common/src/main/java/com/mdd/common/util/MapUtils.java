package com.mdd.common.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Map工具类
 */
public class MapUtils {

    /**
     * JSON转 Map<String, String>
     *
     * @author fzr
     * @param json 对象
     * @return Map<String, String>
     */
    public static Map<String, String> jsonToMap(String json){
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        return JSON.parseObject(json, type);
    }

    /**
     * JSON转 Map<String, Object>
     *
     * @author fzr
     * @param json 对象
     * @return Map<String, Object>
     */
    public static Map<String, Object> jsonToMapAsObj(String json){
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        return JSON.parseObject(json, type);
    }

    /**
     * JSON转Map<String, String>
     *
     * @author fzr
     * @param object 对象
     * @return Map<String, String>
     */
    public static Map<String, String> objectToMap(Object object){
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        return JSON.parseObject(JSONObject.toJSONString(object), type);
    }


    /**
     * 对象类型Map合并
     *
     * @author fzr
     * @param map 对象
     * @return Map<String, Object>
     */
    public static Map<String, Object> mergeMapByObj(Map<String, Object> map, Map<String, Object> map1){
        HashMap<String, Object> map2 = new HashMap<>();
        map2.putAll(map);
        map2.putAll(map1);
        return map2;
    }


    /**
     * 字符串类型Map合并
     *
     * @author fzr
     * @param map 对象
     * @return Map<String, String>
     */
    public static Map<String, String> mergeMapByStr(Map<String, String> map, Map<String, String> map1){
        HashMap<String, String> map2 = new HashMap<>();
        map2.putAll(map);
        map2.putAll(map1);
        return map2;
    }

}

package com.hxkj.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.hxkj.common.config.GlobalConfig;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/**
 * 常用工具集合
 */
public class ToolsUtil {

    /**
     * 制作UUID
     *
     * @author fzr
     * @return String
     */
    public static String makeUUID(){
        return UUID.randomUUID().toString().replaceAll("-","").substring(0,32);
    }

    /**
     * 制作MD5
     *
     * @author fzr
     * @param data 需加密的数据
     * @return String
     */
    public static String makeMd5(String data){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte [] array = md5.digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 生成唯一Token
     *
     * @author fzr
     * @return String
     */
    public static String makeToken() {
        long millisecond =  System.currentTimeMillis();
        String randStr =  ToolsUtil.randomString(8);
        String secret  = GlobalConfig.secret;
        String token   = ToolsUtil.makeMd5(ToolsUtil.makeUUID() + millisecond + randStr);
        return ToolsUtil.makeMd5(token + secret) + ToolsUtil.randomString(6);
    }

    /**
     * 返回随机字符串
     *
     * @author fzr
     * @param length 要生成的长度
     * @return String
     */
    public static String randomString(int length) {
        Random random = new Random();
        StringBuilder stringBuffer = new StringBuilder();
        String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int strLength = str.length();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(strLength);
            stringBuffer.append(str.charAt(index));
        }
        return stringBuffer.toString();
    }

    /**
     * 返回随机数字字符串
     *
     * @author fzr
     * @param length 要生成的长度
     * @return String
     */
    public static String randomInt(int length) {
        Random random = new Random();
        StringBuilder stringBuffer = new StringBuilder();
        String str = "0123456789";
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(10);
            stringBuffer.append(str.charAt(index));
        }
        return stringBuffer.toString();
    }

    /**
     * JSON转map
     *
     * @author fzr
     * @param json 对象
     * @return Map
     */
    public static Map<String, Object> jsonToMap(String json){
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        return JSON.parseObject(json, type);
    }

    /**
     * 列表转字符串
     *
     * @author fzr
     * @param list 列表
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
     * @param list 列表
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

}

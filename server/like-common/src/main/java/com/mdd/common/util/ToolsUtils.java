package com.mdd.common.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.mdd.common.config.GlobalConfig;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/**
 * 常用工具集合
 */
public class ToolsUtils {

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
                sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
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
        String randStr =  ToolsUtils.randomString(8);
        String secret  = GlobalConfig.secret;
        String token   = ToolsUtils.makeMd5(ToolsUtils.makeUUID() + millisecond + randStr);
        return ToolsUtils.makeMd5(token + secret) + ToolsUtils.randomString(6);
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
     * 转换存储单位: KB MB GB TB
     *
     * @author fzr
     * @return String
     */
    public static String storageUnit(Long size) {
        if (size == null) {
            return "0B";
        }
        if (size < 1024) {
            return size + "B";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            return size + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            size = size * 100;
            return (size / 100) + "." + (size % 100) + "MB";
        } else {
            size = size * 100 / 1024;
            return (size / 100) + "." + (size % 100) + "GB";
        }
    }

    /**
     * 下载文件
     *
     * @author fzr
     * @param urlString (文件网址)
     * @param savePath (保存路径,如: /www/uploads)
     * @param filename (保存名称,如: aa.png)
     * @throws IOException 异常
     */
    public static void download(String urlString, String savePath, String filename) throws IOException {
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        con.setConnectTimeout(20 * 1000);
        File sf = new File(savePath);
        if (!sf.exists()) {
            if (sf.mkdirs()) {
                throw new IOException("创建目录失败");
            }
        }
        try (InputStream in = con.getInputStream();
             OutputStream out = new FileOutputStream(sf.getPath() + "\\" + filename)) {
            byte[] buff = new byte[1024];
            int n;
            while ((n = in.read(buff)) >= 0) {
                out.write(buff, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
     * @return Map<String, String>
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
     * @return Object
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
     * @return Object
     */
    public static Map<String, String> mergeMapByStr(Map<String, String> map, Map<String, String> map1){
        HashMap<String, String> map2 = new HashMap<>();
        map2.putAll(map);
        map2.putAll(map1);
        return map2;
    }

}

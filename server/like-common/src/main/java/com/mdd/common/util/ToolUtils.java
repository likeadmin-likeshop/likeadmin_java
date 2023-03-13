package com.mdd.common.util;

import com.mdd.common.config.GlobalConfig;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/**
 * 常用工具集合
 */
public class ToolUtils {

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
        String randStr =  ToolUtils.randomString(8);
        String secret  = GlobalConfig.secret;
        String token   = ToolUtils.makeMd5(ToolUtils.makeUUID() + millisecond + randStr);
        return ToolUtils.makeMd5(token + secret) + ToolUtils.randomString(6);
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
     * @throws IOException IO异常
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

}

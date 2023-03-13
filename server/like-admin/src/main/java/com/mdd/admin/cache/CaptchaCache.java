package com.mdd.admin.cache;

import com.mdd.common.util.*;

/**
 * 验证码缓存器
 */
public class CaptchaCache {

    public static String get(String uuid) {
        String ip = IpUtils.getIpAddress().replaceAll("\\.", "");
        String captchaKey = YmlUtils.get("like.captcha.token") + ip + ":" + uuid;
        Object code = RedisUtils.get(captchaKey);

        if (StringUtils.isNull(code) || StringUtils.isEmpty(code.toString())) {
            return "";
        }

        RedisUtils.del(captchaKey);
        return code.toString();
    }

    public static void set(String code, String uuid) {
        String ip   = IpUtils.getIpAddress().replaceAll("\\.", "");
        String verifyKey = YmlUtils.get("like.captcha.token") + ip + ":" + uuid;
        long expireTime = Long.parseLong(YmlUtils.get("like.captcha.expire"));

        RedisUtils.set(verifyKey, code.toLowerCase(), expireTime);
    }

}

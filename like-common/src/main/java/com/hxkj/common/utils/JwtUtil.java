package com.hxkj.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    /**
     * 过期时间(秒)
     */

    private static final Integer expire = 7200;

    /**
     * 加密密钥
     */

    private static final String secret = "EEE";

    /**
     * 请求头字段名
     */

    private static final String header = "token";

    /**
     * 创建token
     *
     * @author fzr
     * @param map 参数
     */
    public static String createToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, expire);

        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime())
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * token是否过期
     * @author fzr
     * @param token token
     */
    public void isTokenExpired(String token) {
        JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }

    /**
     * 获取token中的payload
     * @author fzr
     * @param token token
     * @return DecodedJWT
     */
    public static DecodedJWT getToken(String token) {
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }

}

package com.hxkj.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

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
     * @param userId 用户ID
     */
    public static String createToken(Integer userId) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, expire);

        return JWT.create()
                .withAudience(String.valueOf(userId))   // 签发对象
                .withIssuedAt(new Date())               // 发行时间
                .withExpiresAt(instance.getTime())      // 有效时间
                .withClaim("userId", userId)  // 载荷
                .sign(Algorithm.HMAC256(secret));       // 加密
    }

    /**
     * 检验合法性
     *
     * @param token 令牌
     */
    public static void verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            verifier.verify(token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 获取签发对象
     *
     * @param token 令牌
     * @return userId
     */
    public static Integer getAudience(String token) {
        Integer audience = null;
        try {
            audience = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        } catch (JWTDecodeException j) {
            System.out.println(j.getMessage());
        }
        return audience;
    }

    /**
     * 通过载荷名字获取载荷的值
     *
     * @param token 令牌
     * @param name 载荷名称
     * @return Claim
     */
    public static Claim getClaimByName(String token, String name){
        return JWT.decode(token).getClaim(name);
    }

}

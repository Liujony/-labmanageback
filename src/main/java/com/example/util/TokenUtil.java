package com.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZC0
 * @Date: 2023/05/07/22:43
 * @Description: 代码完成
 */
@Component
public class TokenUtil {

    @Value("${token.secretKey:default_value}")
    private String secretKey;

    /**
     * 加密token.
     */
    public String getToken(String userId, Integer userRole) {
        //这个是放到负载payLoad 里面,魔法值可以使用常量类进行封装.
        return JWT
                .create()
                .withClaim("userId" ,userId)
                .withClaim("userRole", userRole)
                .withClaim("timeStamp", System.currentTimeMillis())
                .sign(Algorithm.HMAC256(secretKey));
    }

    /**
     * 解析token.
     * {
     * "userId": "weizhong",
     * "userRole": "ROLE_ADMIN",
     * "timeStamp": "134143214"
     * }
     */
    public Map<String, String> parseToken(String token) {
        HashMap<String, String> map = new HashMap<String, String>();
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(secretKey))
                .build().verify(token);
        Claim userId = decodedjwt.getClaim("userId");
        Claim userRole = decodedjwt.getClaim("userRole");
        Claim timeStamp = decodedjwt.getClaim("timeStamp");
        map.put("userId", userId.asString());
        map.put("userRole", String.valueOf(userRole));
        map.put("timeStamp", timeStamp.asLong().toString());
        return map;
    }

    /**
     * 根据token获取userId
     * */
    public static long getUserId(String token) {
        try {
            return Long.parseLong(JWT.decode(token).getAudience().get(0));
        }catch (JWTDecodeException e) {
            return -1;
        }
    }

    /**
     * 根据token获取自定义数据info
     * */
    public static Integer getInfo(String token) {
        try {
            return JWT.decode(token).getClaim("userRole").asInt();
        }catch (JWTDecodeException e) {
            return null;
        }
    }

}

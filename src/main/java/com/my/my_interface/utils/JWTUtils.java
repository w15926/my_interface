package com.my.my_interface.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
  private static final String SIGN = "dsh**&&^^${]sad..><//~~~~2--++@"; // 加密签名

  /*
   * 生成 token
   */
  public static String getToken(Map<String, String> map) {
    Calendar instance = Calendar.getInstance();
    instance.add(Calendar.DATE, 7); // 默认七天过期

    // 创建 JWT builder
    JWTCreator.Builder builder = JWT.create();

    // payload
    map.forEach((k, v) -> {
      builder.withClaim(k, v);
    });

    // 过期时间、签名
    String token = builder
            .withExpiresAt(instance.getTime())
            .sign(Algorithm.HMAC256(SIGN));

    return token;
  }

  /*
   * 验证 token
   */
  public static DecodedJWT verifyToken(String token) {
    return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
  }
}
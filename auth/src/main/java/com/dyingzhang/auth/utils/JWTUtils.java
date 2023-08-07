package com.dyingzhang.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

/**
 * @author DyingZhang
 * @date 2023-08-04 下午 10:42
 * @discription
 */
public class JWTUtils {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String subject, int expiration) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 24 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public static boolean isTokenExpired(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Date expiration = claims.getExpiration();
            Date now = new Date();
            return expiration.before(now); // 如果过期时间在当前时间之前，返回true表示Token已过期
        } catch (Exception e) {
            // Token解析失败或格式错误等情况
            return true; // 当解析失败时，也视为Token已过期
        }
    }
}

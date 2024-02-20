package cn.hungslabsec.core.common.utils;

/**
 * @author Hungs
 * @date 2024/2/19
 * @Description Description of the file.
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 *
 * @author xiongxiaoyang
 * @date 2022/5/17
 */
@Component
@Slf4j
public class JwtUtils {

    /**
     * 注入JWT加密密钥
     */
    @Value("E66559580A1ADF48CDD928516062F12E")
    private String secret;

    /**
     * 过期时间(单位:秒)
     */
    private static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds

    /**
     * 根据用户ID生成JWT
     *
     * @param claims    用户信息
     * @return JWT
     */
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .setClaims(claims)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    /**
     * 解析JWT返回用户ID
     *
     * @param token     JWT
     * @return 用户ID
     */
    public Claims parseToken(String token) {
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
            // OK, we can trust this JWT
            return claimsJws.getBody();
        } catch (JwtException e) {
            log.warn("JWT解析失败:{}", token);
            // don't trust the JWT!
        }
        return null;
    }

}

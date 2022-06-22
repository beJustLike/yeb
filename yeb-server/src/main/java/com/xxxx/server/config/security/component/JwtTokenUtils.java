package com.xxxx.server.config.security.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt Token工具类，获取登录时token
 */
@Component
public class JwtTokenUtils {

    //用户名的key
    public static final String CLAIM_KEY_USERNAME="sub";

    //jwt创建时间
    public static final String CLAIM_KEY_CREATED="created";

    //加解密使用的密钥，使用value注解获取
    @Value("${jwt.secret}")
    private String secret;

    //失效时间
    @Value("${jwt.expiration}")
    private Long expiration;

    //根据用户信息生成token
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);

    }

    //根据负载生成Jwt Token
    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    //生成Token失效时间
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+expiration * 1000);
    }

    //从token中获取登录用户名
    public String getUserNameFromToken(String token){
        String userName;
        try {
            Claims claims = getClaimsFromToken(token);
            userName = claims.getSubject();
        }catch (Exception e){
            userName = null;
        }
        return userName;
    }

    //从token中获取负载
    private Claims getClaimsFromToken(String token){
        Claims claims = null;
        try {

            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }

    //判断token是否有效
    public boolean validateToken(String token,UserDetails userDetails){
        String userName = getUserNameFromToken(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    //判断token是否失效
    private boolean isTokenExpired(String token){
        Date expireDate = getExpiredDateToken(token);
        return expireDate.before(new Date());
    }

    //从token中获取失效时间
    private Date getExpiredDateToken(String token){
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    //判断token是否可以被刷新
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    //token失效后生成新的token
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

}

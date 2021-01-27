package com.jidong.ccadui.jwt;

import com.jidong.ccadui.domain.member.service.MemberOAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${oauth.token.secretKey}")
    private String secretKey;

    /**
     * JWT Token 생성
     *
     * @param key
     * @param memberOAuth
     * @param subject
     * @return
     * @throws Exception
     */
    public String create(String key, MemberOAuth memberOAuth, String subject) throws Exception {
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 60); // 1시간

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setSubject(subject)
                .setExpiration(expireTime)
                .claim(key, memberOAuth)
                .signWith(SignatureAlgorithm.HS256, this.generateKey()).compact();
    }

    private byte[] generateKey() {
        byte[] key = null;
        try {
            key = secretKey.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("Making JWT Key Error ::: {}", e.getMessage());
        }
        return key;
    }

    /**
     * 토큰 검증
     *
     * @param token
     * @param key
     * @return
     */
    public boolean validToken(String token, String key) {
        Map<String, Object> value = getTokenInfo(token, key);
        if (value.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 토큰 정보 추출
     *
     * @param key
     * @return
     */
    public Map<String, Object> getTokenInfo(String token, String key) {
        Jws<Claims> claims;
        Map<String, Object> value = new HashMap<>();

        try {
            claims = Jwts.parser().setSigningKey(secretKey.getBytes("UTF-8")).parseClaimsJws(token);
            value = (LinkedHashMap<String, Object>) claims.getBody().get(key);
        } catch (ExpiredJwtException e) {
            log.error("JWT Token Expried Error : " + token);
            return value;
        } catch (JwtException exception) {
            log.error("JWT Token Exception : " + token);
            return value;
        } catch (Exception e) {
            log.error("JWT UnauthorizedException : " + token);
            return value;
        }

        return value;
    }
}

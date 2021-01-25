package com.jidong.ccadui.jwt;
import com.jidong.ccadui.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

       @Value("${oauth.token.secretKey}")
       private String secretKey;

       public <T> String create(String key, T data, String subject) throws Exception{
      		Date expireTime = new Date();
      		expireTime.setTime(expireTime.getTime() + 1000 * 60 * 60); // 1시간

      		return Jwts.builder()
                         .setHeaderParam("typ", "JWT")
                         .setHeaderParam("regDate", System.currentTimeMillis())
                         .setSubject(subject)
                         .setExpiration(expireTime)
                         .claim(key, data)
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

       public boolean isUsable(String jwt) {
              try {
                     Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
                     return true;

              } catch (Exception e) {
                     log.error("JWT UnauthorizedException : " + e.getMessage(), e);
                     throw new UnauthorizedException();
                     /*
                      * 개발환경!!! return false;
                      */
              }
       }

    public Map<String, Object> get(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String jwt = request.getParameter("accessToken");
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey.getBytes("UTF-8")).parseClaimsJws(jwt);
        } catch (Exception e) {
            log.error("JWT UnauthorizedException : " + e.getMessage(), e);
            throw new UnauthorizedException();

            /*
             * 개발환경 Map<String,Object> testMap = new HashMap<>(); testMap.put("memberId",
             * 2); return testMap;
             */
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (LinkedHashMap<String, Object>) claims.getBody().get(key);
        return value;
    }
}

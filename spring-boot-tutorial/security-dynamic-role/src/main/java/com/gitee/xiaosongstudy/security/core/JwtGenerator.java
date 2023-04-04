package com.gitee.xiaosongstudy.security.core;

import com.gitee.xiaosongstudy.base.asserts.Asserts;
import com.gitee.xiaosongstudy.security.config.AppProperties;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

/**
 * jwt生成器 <br/>
 *
 * @author hopeurl@shiping.song
 * @date 2022/10/2 19:31
 */
@Component
@Slf4j
public class JwtGenerator {
    /**
     * 为什么类型是 Key. 因为我们通过第三方的 Jwt 生成 jwt字符串时候，需要使用 Key类型的对象
     */
    private final Key accessKey;
    private final Key refreshKey;
    private final long accessJwtExpire;
    private final long refreshJwtExpire;

    public JwtGenerator(AppProperties appProperties) {
        Asserts.notNull(appProperties, "核心配置文件注入失败");
        accessKey = new SecretKeySpec(Base64.getDecoder().decode(appProperties.getJwt().getAccessKey()), "HmacSHA512");
        refreshKey = new SecretKeySpec(Base64.getDecoder().decode(appProperties.getJwt().getRefreshKey()), "HmacSHA512");
        accessJwtExpire = appProperties.getJwt().getAccessTokenExpire();
        refreshJwtExpire = appProperties.getJwt().getRefreshTokenExpire();
    }

    /**
     * 创建 access_token
     *
     * @param userDetails 用户信息
     * @return 用户信息
     */
    public String createAccessJwt(UserDetails userDetails) {
        return createJwt(userDetails, accessJwtExpire, accessKey);
    }

    /**
     * 创建refresh_token
     *
     * @param userDetails
     * @return
     */
    public String createRefreshJwt(UserDetails userDetails) {
        return createJwt(userDetails, refreshJwtExpire, refreshKey);
    }

    /**
     * 验证refresh_token
     *
     * @param jwt 待校验refresh_token
     * @return 校验结果
     */
    public JwtParseInfo validateRefreshJwt(String jwt) {
        return validateJwt(jwt, refreshKey);
    }

    /**
     * 验证access_token
     *
     * @param jwt 待校验access_token
     * @return 校验结果
     */
    public JwtParseInfo validateAccessJwt(String jwt) {
        return validateJwt(jwt, accessKey);
    }

    /**
     * 校验jwt信息的合法性
     *
     * @param jwt 待校验的jwt信息
     * @param key 待校验的jwt key
     * @return jwt解析类信息
     */
    private JwtParseInfo validateJwt(String jwt, Key key) {
        JwtParseInfo jpi = new JwtParseInfo();
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
            jpi.setClaims(claims);
            jpi.setSignatureStatus(SignatureStatus.correct);
            return jpi;   // 正常
        } catch (ExpiredJwtException exception) {
            log.info("jwt过期了");
            jpi.setSignatureStatus(SignatureStatus.expired);
            return jpi; // 过期了
        } catch (MalformedJwtException | io.jsonwebtoken.security.SignatureException |
                 UnsupportedJwtException exception) {
            log.error("jwt可能被篡改了");
            jpi.setSignatureStatus(SignatureStatus.tamper);
            return jpi;
        }
    }

    /**
     * 生成Jwt的通用方法
     *
     * @param userDetails 里面就是包含了用户名， 权限信息
     * @param expire      过期时间
     * @param key         当前待生成Key
     * @return 生成结果
     */
    private String createJwt(UserDetails userDetails, Long expire, Key key) {
        Map<String, Object> extractData = new HashMap<>();
        Collection<? extends GrantedAuthority> grantedAuthorities = userDetails.getAuthorities();
        //获取到权限信息
        List<String> authorities = grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        //设置权限信息
        extractData.put("authorities", authorities);
        //设置用户名
        extractData.put("name", userDetails.getUsername());

        return Jwts.builder()
                .setClaims(extractData)
                .setIssuedAt(new Date()) // token的颁发时间
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}

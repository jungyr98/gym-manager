package com.potato.core.auth;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.potato.core.auth.vo.JwtToken;
import com.potato.core.enums.JwtEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

	@Value("${auth.jwt.secret}")
	private String secretKey;
	@Value("${token.ACCESS_TOKEN.EXPIRE_TIME}")
    private String ACCESS_TOKEN_EXPIRE_TIME;
	@Value("${token.REFRESH_TOKEN.EXPIRE_TIME}")
    private String REFRESH_TOKEN_EXPIRE_TIME;

	// AccessToken, RefreshToken 생성
	public JwtToken generateToken(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

		long accessExpireTime = Long.parseLong(ACCESS_TOKEN_EXPIRE_TIME);
        long refreshExpireTime = Long.parseLong(REFRESH_TOKEN_EXPIRE_TIME);

        long now = new Timestamp(System.currentTimeMillis()).getTime();

		// Access Token 생성
        Date accessTokenExpiresIn = new Date(now + accessExpireTime);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)), SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + refreshExpireTime))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)), SignatureAlgorithm.HS256)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

	}

	// Jwt Token 복호화 후 정보 추출
	public Authentication getAuthentication(String token) {

    	// Jwt 토큰 복호화
        Claims claims = parseClaims(token);

        if (claims == null || claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
        		Arrays.stream(claims.get("auth").toString().split(","))
                	.map(SimpleGrantedAuthority::new)
                	.collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰 정보 검증
    public JwtEnum validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
                    .build()
                    .parseClaimsJws(token);
            return JwtEnum.OK;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            return JwtEnum.WRONG;
        } catch (ExpiredJwtException e) {
            return JwtEnum.EXPIRE;
        } catch (UnsupportedJwtException e) {
            return JwtEnum.NOT_SUPPORT;
        } catch (IllegalArgumentException e) {
            return JwtEnum.NOT_EXIST_CLAIMS;
        }
    }

   private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}

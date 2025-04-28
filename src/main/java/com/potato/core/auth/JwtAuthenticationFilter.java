package com.potato.core.auth;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.potato.core.enums.JwtEnum;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    		throws ServletException, IOException {
		String requestURI = request.getRequestURI();

	    // permitAll() 엔드포인트 제외
	    if (requestURI.startsWith("/api/v1/auth/signup") ||
	        requestURI.startsWith("/api/v1/auth/signin") ||
	        requestURI.startsWith("/api/v1/auth/refresh") ||
	        requestURI.startsWith("/api/v1/visit")) {
	        filterChain.doFilter(request, response); // 필터를 건너뜁니다.
	        return;
	    }

		// 1. Request Header에서 JWT 토큰 추출
        String token = resolveToken((HttpServletRequest) request);

        try {
            log.info(" ■ JWT filter : accessToken : " + token );
        }catch (Exception e){
            log.info(e.getMessage());
        }

        JwtEnum validateState = jwtTokenProvider.validateToken(token);

        // 2. validateToken으로 토큰 유효성 검사
        if (token != null && validateState.equals(JwtEnum.OK)) {
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
		}

        filterChain.doFilter(request, response);

	}

	// Request Header에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}

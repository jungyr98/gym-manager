package com.potato.core.auth.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potato.core.account.repository.AccountRepository;
import com.potato.core.account.vo.AccountVO;
import com.potato.core.auth.JwtTokenProvider;
import com.potato.core.auth.repository.AuthRepository;
import com.potato.core.auth.vo.JwtToken;
import com.potato.core.entity.RefreshTokens;
import com.potato.core.entity.SubscribeUser;
import com.potato.core.enums.ApiCodeEnum;
import com.potato.core.enums.JwtEnum;
import com.potato.core.vo.ResponseJson;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

	private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;

    /**
     * 회원 가입
     */
    @Override
    public SubscribeUser signUp(AccountVO accountVO) {

    	SubscribeUser entity = new SubscribeUser();
    	entity.setSuId(accountVO.getId());
    	entity.setSuPw(passwordEncoder.encode(accountVO.getPw()));
    	entity.setSuName(accountVO.getName());
    	entity.setRegDate(Timestamp.valueOf(LocalDateTime.now()));
    	accountRepository.save(entity);

    	return entity;
    }

    /**
     * 로그인
     */
    @Transactional
    @Override
    public ResponseJson<Object> signIn(AccountVO accountVO) {

    	// 1. 아이디 존재 여부 확인
    	Long count = accountRepository.countBySuId(accountVO.getId());

    	// 1-1. 아이디 존재하지 않을 시 return
    	if(count == 0) {
    		return ResponseJson.builder()
                    .status(907)
                    .message("error.907")
                    .build();
    	}

    	// 2. 구독 계정 객체 조회
    	SubscribeUser entity = accountRepository.findBySuId(accountVO.getId());

    	// 3. 비밀번호 매치 확인. 일치하지 않을 시 return
    	if (!passwordEncoder.matches(accountVO.getPw(), entity.getSuPw())) {
            return ResponseJson.builder()
                    .status(908)
                    .message("error.908")
                    .build();
        }

    	// 4. 구독자 계정 entity 기반으로 AccountVO 객체 생성
    	UserDetails userDetails = new AccountVO(entity);

    	// 5. 계정 객체 기반으로 Authentication 객체 생성
    	Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // 6. Authentication 객체 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        // 7. Refresh Token 정보 테이블에 삽입 또는 수정
        RefreshTokens refreshEntity = authRepository.findByUserId(accountVO.getId());

        if(ObjectUtils.isEmpty(refreshEntity)) { // 데이터 없을 시 user ID 세팅 후 삽입 처리
        	refreshEntity = new RefreshTokens();
        	refreshEntity.setUserId(accountVO.getId());
        	refreshEntity.setToken(jwtToken.getRefreshToken());
        	authRepository.save(refreshEntity);
        } else {
        	updateToken(refreshEntity, jwtToken);
        }

        Map<String, Object> param = new HashMap<>();
        param.put("account", entity);
        param.put("token", jwtToken);

    	return ResponseJson.builder()
    			.status(ApiCodeEnum.SUCCESS.getNumber())
    			.message("OK")
    			.result(param)
    			.build();

    }

    /**
     * 토큰 재발급
     * With refreshToken
     * 회전식으로 access 토큰 재발급 시 refresh 토큰도 재발급 처리
     */
    @Transactional
    @Override
    public ResponseJson<Object> updateToken(String refreshToken, HttpServletResponse response) {

    	// 새로운 토큰 객체 생성
    	JwtToken newToken = new JwtToken();

    	// 1. 기존 쿠키에 저장된 토큰으로 DB의 Refresh 토큰 정보 조회
    	RefreshTokens refreshInfo = authRepository.findByToken(refreshToken);

    	if(refreshInfo == null) {
    		return ResponseJson.builder()
                    .status(JwtEnum.EMPTY_TOKEN.getCode())
                    .message(JwtEnum.EMPTY_TOKEN.getMessage())
                    .build();
    	}

    	// 2. Refresh 토큰 검증
    	JwtEnum status = jwtTokenProvider.validateToken(refreshInfo.getToken());

    	// 3. Refresh 토큰이 유효할 시, 새로운 Access 토큰 발급 실행
    	if(status.getCode() == JwtEnum.OK.getCode()) {

        	// 3-1. 구독 계정 객체 조회
        	SubscribeUser entity = accountRepository.findBySuId(refreshInfo.getUserId());

        	// 3-2. 구독자 계정 entity 기반으로 AccountVO 객체 생성
        	UserDetails userDetails = new AccountVO(entity);

        	// 3-3. 계정 객체 기반으로 Authentication 객체 생성
        	Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // 3-4. Authentication 객체 정보를 기반으로 새로운 JWT 토큰 생성
        	newToken = jwtTokenProvider.generateToken(authentication);

        	// 3-5. DB 데이터 수정 처리
        	updateToken(refreshInfo, newToken);

    	} else {
    		throw new RuntimeException(status.getMessage());
    	}

    	// Refresh 토큰 쿠키 세팅
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", newToken.getRefreshToken())
        		.httpOnly(true)
        		.secure(false)
        		.sameSite("Lax")
        		.path("/")
        		.build();

        response.addHeader("Set-Cookie", refreshCookie.toString());

    	Map<String, Object> param = new HashMap<>();
        param.put("token", newToken);

    	return ResponseJson.builder()
    			.status(ApiCodeEnum.SUCCESS.getNumber())
    			.message("OK")
    			.result(param)
    			.build();
    }

    /**
     * DB Refresh 토큰 수정 처리
     * @param refreshEntity
     */
    private void updateToken(RefreshTokens refreshEntity, JwtToken newToken) {
    	refreshEntity.setToken(newToken.getRefreshToken());
    }

}

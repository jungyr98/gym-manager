package com.potato.core.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.potato.core.account.vo.AccountVO;
import com.potato.core.auth.service.AuthService;
import com.potato.core.auth.vo.JwtToken;
import com.potato.core.entity.SubscribeUser;
import com.potato.core.enums.ApiCodeEnum;
import com.potato.core.vo.ResponseJson;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "인증 API", description = "컨트롤러에 대한 설명입니다.")
public class AuthController {

	private final AuthService authService;

	@Operation(summary="회원가입")
	@PostMapping("/api/v1/auth/signup")
	public ResponseEntity<ResponseJson<AccountVO>> signUp(@RequestBody AccountVO accountVO) {
		authService.signUp(accountVO);
		return ResponseEntity.ok(ResponseJson.<AccountVO>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(null)
				.build());
	}


	@Data
    public static class AuthSigninVO {
        SubscribeUser account;   // 사용자 계정 정보
        JwtToken token; 		 // 사용자 토큰 정보
    }

	@Operation(summary="로그인")
	@PostMapping("/api/v1/auth/signin")
	public ResponseEntity<ResponseJson<AuthSigninVO>> signIn(@RequestBody AccountVO accountVO) {
		ResponseJson<Object> res = authService.signIn(accountVO);
		AuthSigninVO authSigninVo = new ObjectMapper().convertValue(res.getResult(), AuthSigninVO.class);
		return ResponseEntity.ok(ResponseJson
                .<AuthSigninVO>builder()
                .status(res.getStatus())
                .message("OK")
                .result(authSigninVo)
                .build());
	}

	@Operation(summary="토큰 재발급")
	@PutMapping("/api/v1/auth/refresh")
	public ResponseEntity<ResponseJson<Object>> refresh(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String refreshToken = null;

		if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        if (refreshToken == null) {
            return ResponseEntity.ok(ResponseJson.<Object>builder()
    				.status(401)
    				.message("refresh token missing")
    				.result(null)
    				.build());
        }

		ResponseJson<Object> res = authService.updateToken(refreshToken, response);
		Object newToken = new ObjectMapper().convertValue(res.getResult(), Object.class);

		return ResponseEntity.ok(ResponseJson.<Object>builder()
				.status(res.getStatus())
				.message("OK")
				.result(newToken)
				.build());

	}

}

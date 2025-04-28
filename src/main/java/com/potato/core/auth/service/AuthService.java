package com.potato.core.auth.service;

import com.potato.core.account.vo.AccountVO;
import com.potato.core.entity.SubscribeUser;
import com.potato.core.vo.ResponseJson;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

	/**
	 * 회원가입
	 * @param accountVO
	 * @return
	 */
	public SubscribeUser signUp(AccountVO accountVO);

	/**
	 * 로그인
	 * @param accountVO
	 * @return
	 */
	public ResponseJson<Object> signIn(AccountVO accountVO);

	/**
	 * 토큰 재발급
	 * @param refreshToken
	 * @return
	 */
	public ResponseJson<Object> updateToken(String refreshToken, HttpServletResponse response);

}

package com.potato.core.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.potato.core.entity.RefreshTokens;

@Repository
public interface AuthRepository extends JpaRepository<RefreshTokens, Integer> {

	// 로그인 유저 아이디로 Refresh 토큰 정보 조회
	RefreshTokens findByUserId(String userId);

	// Refresh 토큰으로 토큰 정보 조회
	RefreshTokens findByToken(String refreshToken);

}

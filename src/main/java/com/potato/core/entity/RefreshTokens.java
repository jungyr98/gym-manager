package com.potato.core.entity;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="refresh_tokens")
@Schema(description="로그인 유저의 refresh 토큰 정보")
@Data
public class RefreshTokens {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="고유 식별자")
	@Column(name="rt_seq", nullable=false)
	private int rtSeq;

	@Schema(description="아이디")
	@Column(name="user_id", nullable=false)
	private String userId;

	@Schema(description="Refresh 토큰")
	@Column(name="token", nullable=false)
	private String token;

	@Schema(description="토큰 생성 시간")
	@Column(name="created_at")
	private Date createdAt;

	@Schema(description="토큰 만료 시간")
	@Column(name="expires_at")
	private Date expiresAt;

	@Schema(description="토큰 사용 취소 여부")
	@Column(name="revoked")
	private boolean revoked;
}

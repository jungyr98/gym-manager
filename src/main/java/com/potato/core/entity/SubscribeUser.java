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
@Table(name="subscribe_user")
@Schema(description="구독자 테이블")
@Data
public class SubscribeUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="구독자 시퀀스")
	@Column(name="su_seq", nullable=false)
	private int suSeq;

	@Schema(description="구독자 ID", nullable=false)
	@Column(name="su_id")
	private String suId;

	@Schema(description="구독자 PW", nullable=false)
	@Column(name="su_pw")
	private String suPw;

	@Schema(description="구독자 이름", nullable=false)
	@Column(name="su_name")
	private String suName;

	@Schema(description="등록 일자", nullable=false)
	@Column(name="reg_date")
	private Date regDate;

}

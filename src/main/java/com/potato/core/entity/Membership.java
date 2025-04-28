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
@Table(name="membership")
@Schema(description="회원권 테이블")
@Data
public class Membership {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="회원권 시퀀스")
	@Column(name="membership_seq", nullable=false)
	private int membershipSeq;
	
	@Schema(description="사업장 시퀀스")
	@Column(name="wp_seq", nullable=false)
	private int wpSeq;
	
	@Schema(description="회원권 명", nullable=false)
	@Column(name="membership_name")
	private String membershipName;
	
	@Schema(description="회원권 기간", nullable=false)
	@Column(name="membership_period")
	private String membershipPeriod;
	
	@Schema(description="등록자 ID", nullable=false)
	@Column(name="reg_id")
	private String regId;

	@Schema(description="등록 일자", nullable=false)
	@Column(name="reg_date")
	private Date regDate;
	
}

package com.potato.core.entity;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="workplace_member")
@Schema(description="사업장 회원 테이블")
@Data
public class WorkplaceMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="회원 시퀀스")
	@Column(name="member_seq", nullable=false, insertable = false, updatable = false)
	private int memberSeq;

	@Schema(description="사업장 시퀀스")
	@Column(name="wp_seq", nullable=false)
	private int wpSeq;

	@Schema(description="이름")
	@Column(name="member_name", nullable=false)
	private String memberName;

	@Schema(description="생년월일")
	@Column(name="member_birth", nullable=false)
	private String memberBirth;

	@Schema(description="전화번호")
	@Column(name="member_phone")
	private String memberPhone;

	@Schema(description="성별")
	@Column(name="member_sex", nullable=false)
	private String memberSex;

	@Schema(description="주소")
	@Column(name="member_addr")
	private String memberAddr;

	@Schema(description="최초 생성일자", nullable=false)
	@Column(name="reg_date")
	private Date regDate;

	@Schema(description="결제 내역")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_seq", referencedColumnName="member_seq", nullable=false, insertable = false, updatable = false)
	private PaymentsHistory paymentsHistory;

}

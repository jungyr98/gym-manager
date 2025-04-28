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
@Table(name="workplace")
@Schema(description="구독자 사업장 테이블")
@Data
public class Workplace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="사업장 시퀀스")
	@Column(name="wp_seq", nullable=false)
	private int wpSeq;
	
	@Schema(description="구독자 시퀀스")
	@Column(name="su_seq", nullable=false)
	private int suSeq;
	
	@Schema(description="사업장 명", nullable=false)
	@Column(name="wp_name")
	private String wpName;
	
	@Schema(description="대표자 명")
	@Column(name="wp_representative")
	private String wpRepresentative;
	
	@Schema(description="사업자번호")
	@Column(name="wp_business_number")
	private String wpBusinessNumber;
	
	@Schema(description="전화번호")
	@Column(name="wp_tel_number")
	private String wpTelNumber;
	
	@Schema(description="등록 일자", nullable=false)
	@Column(name="reg_date")
	private Date regDate;
	
}

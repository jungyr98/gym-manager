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
@Table(name="calendar")
@Schema(description="일정관리 테이블")
@Data
public class Calendar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="일정관리 시퀀스")
	@Column(name="cl_seq", nullable=false)
	private int clSeq;
	
	@Schema(description="사업장 시퀀스")
	@Column(name="wp_seq", nullable=false)
	private int wpSeq;
	
	@Schema(description="일정 타입")
	@Column(name="cl_type")
	private String clType;
	
	@Schema(description="일정 내용")
	@Column(name="cl_content")
	private String clContent;

	
	@Schema(description="일정 메모")
	@Column(name="cl_memo")
	private String clMemo;

	
	@Schema(description="작성자 ID", nullable=false)
	@Column(name="reg_id")
	private String regId;

	
	@Schema(description="작성 일자", nullable=false)
	@Column(name="reg_date")
	private Date regDate;


}

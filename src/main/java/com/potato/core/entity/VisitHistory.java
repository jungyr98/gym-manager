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
@Table(name="visit_history")
@Schema(description="방문 내역 테이블")
@Data
public class VisitHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="방문 내역 시퀀스")
	@Column(name="vh_seq", nullable=false)
	private int vhSeq;
	
	@Schema(description="회원 시퀀스", nullable=false)
	@Column(name="member_seq")
	private int memberSeq;
	
	@Schema(description="등록 일자", nullable=false)
	@Column(name="reg_date")
	private Date regDate;
	
}

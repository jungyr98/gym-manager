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
@Table(name="send_message")
@Schema(description="메세지 전송 테이블")
@Data
public class SendMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="메세지 전송 시퀀스")
	@Column(name="sm_seq", nullable=false)
	private int smSeq;
	
	@Schema(description="사업장 시퀀스")
	@Column(name="wp_seq", nullable=false)
	private int wpSeq;
	
	@Schema(description="전송 방법", nullable=false)
	@Column(name="sm_type")
	private String smType;
	
	@Schema(description="전송 제목", nullable=false)
	@Column(name="sm_title")
	private String smTitle;
	
	@Schema(description="전송 내용")
	@Column(name="sm_content")
	private String smContent;
	
	@Schema(description="등록자 ID", nullable=false)
	@Column(name="reg_id")
	private String regId;

	
	@Schema(description="등록 일자", nullable=false)
	@Column(name="reg_date")
	private Date regDate;
	
}

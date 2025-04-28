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
@Table(name="goods")
@Schema(description="제품 테이블")
@Data
public class Goods {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="제품 시퀀스")
	@Column(name="goods_seq", nullable=false)
	private int goodsSeq;
	
	@Schema(description="사업장 시퀀스")
	@Column(name="wp_seq", nullable=false)
	private int wpSeq;
	
	@Schema(description="제품 명", nullable=false)
	@Column(name="goods_name")
	private String goodsName;
	
	@Schema(description="제품 가격")
	@Column(name="goods_price")
	private int goodsPrice;
	
	@Schema(description="제품 수량")
	@Column(name="goods_quantity")
	private int goodsQuantity;
	
	@Schema(description="등록자 ID", nullable=false)
	@Column(name="reg_id")
	private String regId;

	@Schema(description="등록 일자", nullable=false)
	@Column(name="reg_date")
	private Date regDate;
	
}

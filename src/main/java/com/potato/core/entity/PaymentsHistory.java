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
@Table(name="payments_history")
@Schema(description="결제 내역 테이블")
@Data
public class PaymentsHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description="결제내역 시퀀스")
	@Column(name="ph_seq", nullable=false)
	private int phSeq;

	@Schema(description="회원 시퀀스")
	@Column(name="member_seq", nullable=false)
	private int memberSeq;

	@Schema(description="회원 명")
	@Column(name="member_name", nullable=false)
	private String memberName;

	@Schema(description="회원권 시퀀스")
	@Column(name="membership_seq", nullable=false)
	private int membershipSeq;


	@Schema(description="결제자 명")
	@Column(name="ph_name")
	private String phName;

	@Schema(description="결제 금액")
	@Column(name="ph_price")
	private int phPrice;

	@Schema(description="결제 방법")
	@Column(name="ph_type")
	private String phType;

	@Schema(description="결제 일자", nullable=false)
	@Column(name="reg_date")
	private Date regDate;

	@Schema(description="회원권")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "membership_seq", referencedColumnName="membership_seq", nullable=true, insertable = false, updatable = false)
    private Membership membership;

}

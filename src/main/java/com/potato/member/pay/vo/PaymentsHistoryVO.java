package com.potato.member.pay.vo;

import com.potato.core.entity.PaymentsHistory;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PaymentsHistoryVO extends PaymentsHistory {

	private String membershipName;

}

package com.potato.chart.sales.vo;

import com.potato.core.entity.Membership;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SalesChartVO extends Membership {

	private int totalPrice; // 총합 금액
	private String regMonth; // 결제 월
	private String ageGroup; // 연령 그룹
}

package com.potato.chart.visit.vo;

import com.potato.core.entity.VisitHistory;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VisitChartVO extends VisitHistory {

	private String weekDay; // 요일
	private int weekIndex; // 요일 인덱스 (0:월 ~ 6:일)
	private int visitCount; // 방문 횟수

}

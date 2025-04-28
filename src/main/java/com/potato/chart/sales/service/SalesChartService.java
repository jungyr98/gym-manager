package com.potato.chart.sales.service;

import com.potato.chart.sales.vo.SalesChartVO;
import com.potato.core.vo.ResponseListVO;

public interface SalesChartService {

	/**
	 * 년도별 월 매출 통계
	 * @param year
	 * @return
	 */
	public ResponseListVO<SalesChartVO> selectMonthList(String year);

	/**
	 * 회원권 별 매출 통계
	 * @param year
	 * @return
	 */
	public ResponseListVO<SalesChartVO> selectMembershipList(String year);

	/**
	 * 연령별 매출 통계
	 * @param year
	 * @return
	 */
	public ResponseListVO<SalesChartVO> selectAgeList(String year);

}

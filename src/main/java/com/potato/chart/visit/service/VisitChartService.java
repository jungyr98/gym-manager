package com.potato.chart.visit.service;

import com.potato.chart.visit.vo.VisitChartVO;
import com.potato.core.vo.ResponseListVO;

public interface VisitChartService {

	/**
	 * 요일별 방문 통계
	 * @return
	 */
	public ResponseListVO<VisitChartVO> selectList();

}

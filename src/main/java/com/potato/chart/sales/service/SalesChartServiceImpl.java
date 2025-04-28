package com.potato.chart.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.potato.chart.sales.mapper.SalesChartMapper;
import com.potato.chart.sales.vo.SalesChartVO;
import com.potato.core.vo.ResponseListVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SalesChartServiceImpl implements SalesChartService {

	private final SalesChartMapper salesChartMapper;

	/**
	 * 년도별 월별 매출 통계
	 */
	@Override
	public ResponseListVO<SalesChartVO> selectMonthList(String year) {
		List<SalesChartVO> list = salesChartMapper.selectMonthList(year);
		ResponseListVO<SalesChartVO> responseListVO = new ResponseListVO<>(list, 0);

		return responseListVO;
	}

	/**
	 * 회원권 별 매출 통계
	 */
	@Override
	public ResponseListVO<SalesChartVO> selectMembershipList(String year){
		List<SalesChartVO> list = salesChartMapper.selectMembershipList(year);
		ResponseListVO<SalesChartVO> responseListVO = new ResponseListVO<>(list, 0);

		return responseListVO;
	}

	/**
	 * 연령별 매출 통계
	 */
	@Override
	public ResponseListVO<SalesChartVO> selectAgeList(String year) {
		List<SalesChartVO> list = salesChartMapper.selectAgeList(year);
		ResponseListVO<SalesChartVO> responseListVO = new ResponseListVO<>(list, 0);

		return responseListVO;
	}

}

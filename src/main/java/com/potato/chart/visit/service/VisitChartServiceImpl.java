package com.potato.chart.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.potato.chart.visit.mapper.VisitChartMapper;
import com.potato.chart.visit.vo.VisitChartVO;
import com.potato.core.vo.ResponseListVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VisitChartServiceImpl implements VisitChartService {

	private final VisitChartMapper visitChartMapper;

	/**
	 * 요일별 방문 통계
	 */
	@Override
	public ResponseListVO<VisitChartVO> selectList() {
		List<VisitChartVO> list = visitChartMapper.selectList();
		ResponseListVO<VisitChartVO> responseListVO = new ResponseListVO<>(list, 0);

		return responseListVO;
	}

}

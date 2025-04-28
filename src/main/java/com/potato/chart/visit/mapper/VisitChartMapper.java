package com.potato.chart.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.potato.chart.visit.vo.VisitChartVO;

@Mapper
public interface VisitChartMapper {

	/**
	 * 요일별 방문 통계
	 * @return
	 */
	List<VisitChartVO> selectList();

}

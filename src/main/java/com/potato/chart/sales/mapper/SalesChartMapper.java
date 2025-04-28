package com.potato.chart.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.potato.chart.sales.vo.SalesChartVO;

@Mapper
public interface SalesChartMapper {

	/**
	 * 년도별 월 매출 통계
	 * @param year
	 * @return
	 */
	List<SalesChartVO> selectMonthList(@Param("year") String year);

	/**
	 * 회원권별 매출 통계
	 * @param year
	 * @return
	 */
	List<SalesChartVO> selectMembershipList(@Param("year") String year);

	/**
	 * 연령별 매출 통계
	 * @param year
	 * @return
	 */
	List<SalesChartVO> selectAgeList(@Param("year") String year);

}

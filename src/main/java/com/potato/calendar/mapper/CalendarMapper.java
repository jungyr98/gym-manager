package com.potato.calendar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.potato.calendar.vo.CalendarVO;

@Mapper
public interface CalendarMapper {

	/**
	 * 목록 조회
	 * @param wpSeq
	 * @return
	 */
	List<CalendarVO> selectList(@Param("wpSeq") int wpSeq);

	/**
	 * 이번주 일정 목록 조회
	 * @return
	 */
	List<CalendarVO> selectWeekList();

}

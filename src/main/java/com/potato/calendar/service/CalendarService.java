package com.potato.calendar.service;

import java.util.List;

import com.potato.calendar.vo.CalendarVO;
import com.potato.core.vo.ResponseListVO;

public interface CalendarService {

	public List<CalendarVO> selectList(int wpSeq);

	public CalendarVO selectDetail(int clSeq);

	public void insert(CalendarVO calendarVO);

	public void update(CalendarVO calendarVO);

	public void delete(int clSeq);

	/**
	 * 이번주 일정 목록 조회
	 * @return
	 */
	public ResponseListVO<CalendarVO> selectWeekList();

}

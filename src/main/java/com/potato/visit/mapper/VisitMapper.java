package com.potato.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.potato.visit.vo.VisitHistoryVO;

@Mapper
public interface VisitMapper {

	/**
	 * 이번주 방문 목록
	 * @return
	 */
	List<VisitHistoryVO> selectVisitWeekList();

}

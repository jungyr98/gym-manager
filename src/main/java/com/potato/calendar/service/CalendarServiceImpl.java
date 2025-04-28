package com.potato.calendar.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potato.calendar.mapper.CalendarMapper;
import com.potato.calendar.repository.CalendarRepository;
import com.potato.calendar.vo.CalendarVO;
import com.potato.core.entity.Calendar;
import com.potato.core.vo.ResponseListVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

	public final CalendarMapper calendarMapper;
	public final CalendarRepository calendarRepository;

	/**
	 * 목록 조회
	 */
	@Override
	public List<CalendarVO> selectList(int wpSeq) {
		return calendarMapper.selectList(wpSeq);
	}

	/**
	 * 상세 조회
	 */
	@Override
	public CalendarVO selectDetail(int clSeq) {

		Calendar entity = calendarRepository.findByClSeq(clSeq);
		CalendarVO calendarVO = new CalendarVO();

		if(entity != null) {
			calendarVO.setClSeq(entity.getClSeq());
			calendarVO.setWpSeq(entity.getWpSeq());
			calendarVO.setClType(entity.getClType());
			calendarVO.setClContent(entity.getClContent());
			calendarVO.setRegId(entity.getRegId());
			calendarVO.setRegDate(entity.getRegDate());
		}

		return calendarVO;
	}


	/**
	 * 일정 등록
	 */
	@Override
	public void insert(CalendarVO calendarVO) {
		Calendar entity = new Calendar();
		entity.setWpSeq(calendarVO.getWpSeq());
		entity.setClType(calendarVO.getClType());
		entity.setClContent(calendarVO.getClContent());
		entity.setRegId(calendarVO.getRegId());
		entity.setRegDate(calendarVO.getRegDate());

		// 등록 처리
		calendarRepository.save(entity);
	}

	/**
	 * 일정 수정
	 * @throws NotFoundException
	 */
	@Transactional
	@Override
	public void update(CalendarVO calendarVO) {
		Calendar originalEntity = calendarRepository.findByClSeq(calendarVO.getClSeq());

		if(ObjectUtils.isEmpty(originalEntity)) {
			//throw new NotFoundException("Data Not Found");
		}

		originalEntity.setClType(calendarVO.getClType());
		originalEntity.setClContent(calendarVO.getClContent());
	}

	/**
	 * 일정 삭제
	 */
	@Transactional
	@Override
	public void delete(int clSeq) {
		// 삭제 처리
		calendarRepository.deleteByClSeq(clSeq);
	}

	/**
	 * 이번주 일정 목록 조회
	 */
	@Override
	public ResponseListVO<CalendarVO> selectWeekList() {
		List<CalendarVO> list = calendarMapper.selectWeekList();
		ResponseListVO<CalendarVO> responseListVO = new ResponseListVO<>(list, 0);

		return responseListVO;
	}

}

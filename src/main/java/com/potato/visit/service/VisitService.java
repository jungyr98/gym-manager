package com.potato.visit.service;

import com.potato.core.entity.VisitHistory;
import com.potato.core.vo.ResponseListVO;
import com.potato.member.list.vo.MemberVO;
import com.potato.visit.vo.VisitHistoryVO;

public interface VisitService {

	/**
	 * 방문 회원 정보 조회
	 * @param lastPhoneNumber
	 * @return
	 */
	public ResponseListVO<MemberVO> selectMember(String lastPhoneNumber);

	/**
	 * 이번주 방문 목록
	 * @return
	 */
	public ResponseListVO<VisitHistoryVO> selectVisitWeekList();

	/**
	 * 회원별 방문 기록
	 * @param memberSeq
	 * @return
	 */
	public ResponseListVO<VisitHistory> selectMyVisitList(int memberSeq);

}

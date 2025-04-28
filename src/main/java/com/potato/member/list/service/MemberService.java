package com.potato.member.list.service;

import com.potato.core.vo.ResponseListVO;
import com.potato.member.list.vo.MemberVO;

public interface MemberService {

	/**
	 * 목록 조회
	 * @param wpSeq
	 * @param memberName
	 * @param memberPhone
	 * @param activeYn
	 * @param currentPageNo
	 * @param recordCountPerPage
	 * @return
	 */
	public ResponseListVO<MemberVO> selectList(
			int wpSeq
			, String memberName
			, String memberPhone
			, String memberSex
			, String activeYn
			, int currentPageNo
			, int recordCountPerPage);

	/**
	 * 상세 조회
	 * @param memberSeq
	 * @return
	 */
	public MemberVO selectDetail(int memberSeq);

	/**
	 * 회원 등록
	 * @param memberVO
	 */
	public void insert(MemberVO memberVO);

	/**
	 * 회원 수정
	 * @param memberVO
	 */
	public void update(MemberVO memberVO);

	/**
	 * 회원 삭제
	 * @param memberVO
	 */
	public void delete(MemberVO memberVO);

	/**
	 * 이번주 새로 등록된 회원 조회
	 * @return
	 */
	public ResponseListVO<MemberVO> selectWeekList();
}

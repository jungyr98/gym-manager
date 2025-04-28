package com.potato.membership.service;

import com.potato.core.vo.ResponseListVO;
import com.potato.membership.vo.MembershipVO;

public interface MembershipService {

	/**
	 * 목록 조회
	 * @param membershipName
	 * @param membershipPeriod
	 * @param currentPageNo
	 * @param recordCountPerPage
	 * @return
	 */
	public ResponseListVO<MembershipVO> selectList(
			int wpSeq
			, String membershipName
			, String membershipPeriod
			, int currentPageNo
			, int recordCountPerPage);

	/**
	 * 상세 조회
	 * @param membershipSeq
	 * @return
	 */
	public MembershipVO selectDetail(int membershipSeq);

	/**
	 * 회원권 등록
	 * @param membershipVO
	 */
	public void insert(MembershipVO membershipVO);

	/**
	 * 회원권 수정
	 * @param membershipVO
	 */
	public void update(MembershipVO membershipVO);

	/**
	 * 회원권 삭제
	 * @param membershipVO
	 */
	public void delete(MembershipVO membershipVO);

}

package com.potato.member.pay.service;

import com.potato.core.vo.ResponseListVO;
import com.potato.member.pay.vo.PaymentsHistoryVO;

public interface PaymentsHistoryService {

	/**
	 * 목록 조회
	 * @param phName
	 * @param memberName
	 * @param regDate
	 * @param currentPageNo
	 * @param recordCountPerPage
	 * @return
	 */
	public ResponseListVO<PaymentsHistoryVO> selectList(String phName, String memberName, String regDate
			, int currentPageNo, int recordCountPerPage);


	/**
	 * 상세 조회
	 * @param phSeq
	 * @return
	 */
	public PaymentsHistoryVO selectDetail(int phSeq);

	/**
	 * 등록 처리
	 * @param paymentHistoryVO
	 */
	public void insert(PaymentsHistoryVO paymentHistoryVO);

	/*
	 * 수정 처리
	 */
	public void update(PaymentsHistoryVO paymentHistoryVO);

	/**
	 * 삭제 처리
	 * @param paymentHistoryVO
	 */
	public void delete(PaymentsHistoryVO paymentHistoryVO);

	/**
	 * 요일별 결제 내역
	 * @return
	 */
	public ResponseListVO<PaymentsHistoryVO> selectWeekList();

	/**
	 * 회원별 결제 내역
	 * @param memberSeq
	 * @return
	 */
	public ResponseListVO<PaymentsHistoryVO> selectMyPayHistoryList(int memberSeq);

}

package com.potato.member.pay.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potato.core.entity.PaymentsHistory;
import com.potato.core.vo.ResponseListVO;
import com.potato.member.pay.mapper.PaymentsHistoryMapper;
import com.potato.member.pay.repository.PaymentsHistoryRepository;
import com.potato.member.pay.vo.PaymentsHistoryVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaymentsHistoryServiceImpl implements PaymentsHistoryService {

	private final PaymentsHistoryMapper paymentsHistoryMapper;
	private final PaymentsHistoryRepository paymentsHistoryRepository;

	/**
	 * 목록 조회
	 */
	@Override
	public ResponseListVO<PaymentsHistoryVO> selectList(String phName, String memberName, String regDate
			, int currentPageNo, int recordCountPerPage) {

		List<PaymentsHistoryVO> list = null;

		int totalCnt = paymentsHistoryMapper.getListCnt(phName, memberName, regDate);
		if(totalCnt > 0) {
			list = paymentsHistoryMapper.selectList(phName, memberName, regDate, (currentPageNo-1)*recordCountPerPage, recordCountPerPage);
		}

		ResponseListVO<PaymentsHistoryVO> responseListVO = new ResponseListVO<>(list, totalCnt);

		return responseListVO;
	}

	/**
	 * 상세 조회
	 */
	@Override
	public PaymentsHistoryVO selectDetail(int phSeq) {

		PaymentsHistory entity = paymentsHistoryRepository.findByPhSeq(phSeq);

		if(ObjectUtils.isEmpty(entity)) {

		}

		PaymentsHistoryVO paymentHistoryVO = new PaymentsHistoryVO();
		paymentHistoryVO.setPhSeq(entity.getPhSeq());
		paymentHistoryVO.setPhType(entity.getPhType());
		paymentHistoryVO.setPhName(entity.getPhName());
		paymentHistoryVO.setPhPrice(entity.getPhPrice());
		paymentHistoryVO.setMemberSeq(entity.getMemberSeq());
		paymentHistoryVO.setMemberName(entity.getMemberName());
		paymentHistoryVO.setMembershipSeq(entity.getMembershipSeq());
		paymentHistoryVO.setMembershipName(entity.getMembership().getMembershipName());
		paymentHistoryVO.setRegDate(entity.getRegDate());

		return paymentHistoryVO;

	}

	/**
	 * 등록 처리
	 */
	@Override
	public void insert(PaymentsHistoryVO paymentHistoryVO) {

		PaymentsHistory entity = new PaymentsHistory();
		entity.setPhName(paymentHistoryVO.getPhName());
		entity.setPhType(paymentHistoryVO.getPhType());
		entity.setPhPrice(paymentHistoryVO.getPhPrice());
		entity.setMemberSeq(paymentHistoryVO.getMemberSeq());
		entity.setMemberName(paymentHistoryVO.getMemberName());
		entity.setMembershipSeq(paymentHistoryVO.getMembershipSeq());
		entity.setRegDate(paymentHistoryVO.getRegDate());


		// 등록 처리
		paymentsHistoryRepository.save(entity);
	}

	/**
	 * 수정 처리
	 */
	@Transactional
	@Override
	public void update(PaymentsHistoryVO paymentHistoryVO) {

		PaymentsHistory originalEntity = paymentsHistoryRepository.findByPhSeq(paymentHistoryVO.getPhSeq());

		if(ObjectUtils.isEmpty(originalEntity)) {

		}

		// 수정 처리
		originalEntity.setPhName(paymentHistoryVO.getPhName());
		originalEntity.setPhType(paymentHistoryVO.getPhType());
		originalEntity.setPhPrice(paymentHistoryVO.getPhPrice());
		originalEntity.setMemberSeq(paymentHistoryVO.getMemberSeq());
		originalEntity.setMemberName(paymentHistoryVO.getMemberName());
		originalEntity.setMembershipSeq(paymentHistoryVO.getMembershipSeq());
		originalEntity.setRegDate(paymentHistoryVO.getRegDate());
	}

	/**
	 * 삭제 처리
	 */
	@Override
	public void delete(PaymentsHistoryVO paymentHistoryVO) {

	}

	/**
	 * 요일별 매출 통계
	 */
	@Override
	public ResponseListVO<PaymentsHistoryVO> selectWeekList() {
		List<PaymentsHistoryVO> list = paymentsHistoryMapper.selectWeekList();
		ResponseListVO<PaymentsHistoryVO> responseListVO = new ResponseListVO<>(list, 0);

		return responseListVO;
	}

	/**
	 * 회원별 결제 내역
	 */
	@Override
	public ResponseListVO<PaymentsHistoryVO> selectMyPayHistoryList(int memberSeq) {
		List<PaymentsHistory> list = paymentsHistoryRepository.findByMemberSeqOrderByRegDateDesc(memberSeq);

		List<PaymentsHistoryVO> copyList = new ArrayList<>();
		if(ObjectUtils.isNotEmpty(list)) {
			for(PaymentsHistory entity : list) {
				PaymentsHistoryVO vo = new PaymentsHistoryVO();
				vo.setPhSeq(entity.getPhSeq());
				vo.setPhType(entity.getPhType());
				vo.setPhName(entity.getPhName());
				vo.setPhPrice(entity.getPhPrice());
				vo.setMemberSeq(entity.getMemberSeq());
				vo.setMemberName(entity.getMemberName());
				vo.setMembershipSeq(entity.getMembershipSeq());
				vo.setMembershipName(entity.getMemberName());
				vo.setRegDate(entity.getRegDate());
				copyList.add(vo);
			}
		}
		ResponseListVO<PaymentsHistoryVO> responseListVO = new ResponseListVO<>(copyList, 0);

		return responseListVO;
	}

}

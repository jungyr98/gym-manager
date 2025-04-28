package com.potato.membership.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potato.core.entity.Membership;
import com.potato.core.vo.ResponseListVO;
import com.potato.membership.mapper.MembershipMapper;
import com.potato.membership.repository.MembershipRepository;
import com.potato.membership.vo.MembershipVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

	private final MembershipMapper membershipMapper;
	private final MembershipRepository membershipRepository;

	/**
	 * 목록 조회
	 */
	@Override
	public ResponseListVO<MembershipVO> selectList(int wpSeq, String membershipName, String membershipPeriod
			, int currentPageNo, int recordCountPerPage) {
		List<MembershipVO> list = null;

		// 전체 개수 조회
		int totalCnt = membershipMapper.getListCnt(wpSeq, membershipName, membershipPeriod);
		if(totalCnt > 0) {
			list = membershipMapper.selectList(wpSeq, membershipName, membershipPeriod, (currentPageNo-1)*recordCountPerPage, recordCountPerPage);
		}

		ResponseListVO<MembershipVO> responseListVO = new ResponseListVO<>(list, totalCnt);

		return responseListVO;
	}

	/**
	 * 상세 조회
	 */
	@Override
	public MembershipVO selectDetail(int membershipSeq) {
		Membership entity = membershipRepository.findByMembershipSeq(membershipSeq);
		MembershipVO membershipVO = new MembershipVO();

		if(entity != null) {
			membershipVO.setMembershipSeq(entity.getMembershipSeq());
			membershipVO.setWpSeq(entity.getWpSeq());
			membershipVO.setMembershipName(entity.getMembershipName());
			membershipVO.setMembershipPeriod(entity.getMembershipPeriod());
			membershipVO.setRegId(entity.getRegId());
			membershipVO.setRegDate(entity.getRegDate());
		}

		return membershipVO;
	}

	/**
	 * 회원권 등록
	 */
	@Override
	public void insert(MembershipVO membershipVO) {
		Membership entity = new Membership();
		entity.setWpSeq(membershipVO.getWpSeq());
		entity.setMembershipName(membershipVO.getMembershipName());
		entity.setMembershipPeriod(membershipVO.getMembershipPeriod());
		entity.setRegId(membershipVO.getRegId());
		entity.setRegDate(Timestamp.valueOf(LocalDateTime.now()));

		// 등록 처리
		membershipRepository.save(entity);
	}

	/**
	 * 회원권 수정
	 */
	@Transactional
	@Override
	public void update(MembershipVO membershipVO) {
		Membership originalEntity = membershipRepository.findByMembershipSeq(membershipVO.getMembershipSeq());

		if(ObjectUtils.isEmpty(originalEntity)) {

		}

		// 수정 처리
		originalEntity.setMembershipName(membershipVO.getMembershipName());
		if(!originalEntity.getMembershipPeriod().equals(membershipVO.getMembershipPeriod())) {
			// 회원권 기간 값은 수정 가능한 상태일 경우에만 수정 처리
			originalEntity.setMembershipPeriod(membershipVO.getMembershipPeriod());
		}
	}

	/**
	 * 회원권 삭제
	 */
	@Override
	public void delete(MembershipVO membershipVO) {

	}

}

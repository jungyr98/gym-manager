package com.potato.member.list.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potato.core.entity.WorkplaceMember;
import com.potato.core.vo.ResponseListVO;
import com.potato.member.list.mapper.MemberMapper;
import com.potato.member.list.repository.MemberRepository;
import com.potato.member.list.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;
	private final MemberRepository memberRepository;

	/**
	 * 목록 조회
	 */
	@Override
	public ResponseListVO<MemberVO> selectList(int wpSeq, String memberName, String memberPhone,
			String memberSex, String activeYn, int currentPageNo, int recordCountPerPage) {

		List<MemberVO> list = null;
		int totalCnt = memberMapper.getListCnt(wpSeq, memberName, memberPhone, memberSex, activeYn);

		if(totalCnt > 0) {
			list = memberMapper.selectList(wpSeq, memberName, memberPhone, memberSex, activeYn, (currentPageNo-1)*recordCountPerPage, recordCountPerPage);
		}

		ResponseListVO<MemberVO> responseListVO = new ResponseListVO<>(list, totalCnt);

		return responseListVO;
	}

	/**
	 * 상세 조회
	 */
	@Override
	public MemberVO selectDetail(int memberSeq) {
		WorkplaceMember entity = memberRepository.findByMemberSeq(memberSeq);
		MemberVO memberVO = new MemberVO();

		if(entity != null) {
			memberVO.setMemberSeq(entity.getMemberSeq());
			memberVO.setWpSeq(entity.getWpSeq());
			memberVO.setMemberName(entity.getMemberName());
			memberVO.setMemberBirth(entity.getMemberBirth());
			memberVO.setMemberSex(entity.getMemberSex());
			memberVO.setMemberPhone(entity.getMemberPhone());
			memberVO.setMemberAddr(entity.getMemberAddr());
			memberVO.setRegDate(entity.getRegDate());
			if(entity.getPaymentsHistory() != null) {
				memberVO.setStartDate(entity.getPaymentsHistory().getRegDate().toString());
			}
		}

		return memberVO;
	}

	/**
	 * 회원 등록
	 */
	@Override
	public void insert(MemberVO memberVO) {
		WorkplaceMember entity = new WorkplaceMember();
		entity.setWpSeq(memberVO.getWpSeq());
		entity.setMemberName(memberVO.getMemberName());
		entity.setMemberBirth(memberVO.getMemberBirth());
		entity.setMemberSex(memberVO.getMemberSex());
		entity.setMemberPhone(memberVO.getMemberPhone());
		entity.setMemberAddr(memberVO.getMemberAddr());
		entity.setRegDate(Timestamp.valueOf(LocalDateTime.now()));

		// 등록 처리
		memberRepository.save(entity);
	}

	/**
	 * 회원 수정
	 */
	@Transactional
	@Override
	public void update(MemberVO memberVO) {
		WorkplaceMember originalEntity = memberRepository.findByMemberSeq(memberVO.getMemberSeq());

		if(ObjectUtils.isEmpty(originalEntity)) {

		}

		// 수정 처리
		originalEntity.setMemberName(memberVO.getMemberName());
		originalEntity.setMemberBirth(memberVO.getMemberBirth());
		originalEntity.setMemberPhone(memberVO.getMemberPhone());
		originalEntity.setMemberAddr(memberVO.getMemberAddr());
	}

	/**
	 * 회원 삭제
	 */
	@Override
	public void delete(MemberVO memberVO) {

	}

	/**
	 * 이번주 새로 등록된 회원 조회
	 */
	@Override
	public ResponseListVO<MemberVO> selectWeekList() {
		List<MemberVO> list = memberMapper.selectWeekList();
		ResponseListVO<MemberVO> responseListVO = new ResponseListVO<>(list, 0);

		return responseListVO;
	}

}

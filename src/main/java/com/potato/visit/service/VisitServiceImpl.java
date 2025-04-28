package com.potato.visit.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.potato.core.entity.VisitHistory;
import com.potato.core.vo.ResponseListVO;
import com.potato.member.list.mapper.MemberMapper;
import com.potato.member.list.vo.MemberVO;
import com.potato.visit.mapper.VisitMapper;
import com.potato.visit.repository.VisitRepository;
import com.potato.visit.vo.VisitHistoryVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VisitServiceImpl implements VisitService {

	private final MemberMapper memberMapper;
	private final VisitRepository visitRepository;
	private final VisitMapper visitMapper;

	/**
	 * 방문 회원 정보 조회
	 */
	@Override
	public ResponseListVO<MemberVO> selectMember(String lastPhoneNumber) {
		List<MemberVO> list = memberMapper.selectPhoneNumber(lastPhoneNumber);
		if(ObjectUtils.isEmpty(list)) {
			throw new RuntimeException("회원이 존재하지 않습니다.");
		} else if(list.size() == 1) {
			// 방문 등록 처리
			VisitHistory entity = new VisitHistory();
			entity.setMemberSeq(list.get(0).getMemberSeq());
			entity.setRegDate(Timestamp.valueOf(LocalDateTime.now()));
			visitRepository.save(entity);
		}
		ResponseListVO<MemberVO> responseListVO = new ResponseListVO<>(list, 0);

		return responseListVO;
	}

	/**
	 * 이번주 방문 목록
	 */
	@Override
	public ResponseListVO<VisitHistoryVO> selectVisitWeekList() {
		List<VisitHistoryVO> list = visitMapper.selectVisitWeekList();
		ResponseListVO<VisitHistoryVO> responseListVO = new ResponseListVO<>(list, 0);

		return responseListVO;
	}

	/**
	 * 회원별 방문 기록
	 */
	@Override
	public ResponseListVO<VisitHistory> selectMyVisitList(int memberSeq) {
		List<VisitHistory> list = visitRepository.findByMemberSeqOrderByRegDateDesc(memberSeq);
		ResponseListVO<VisitHistory> responseListVO = new ResponseListVO<>(list, 0);

		return responseListVO;
	}

}

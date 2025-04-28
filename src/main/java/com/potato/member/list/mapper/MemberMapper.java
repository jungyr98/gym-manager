package com.potato.member.list.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.potato.member.list.vo.MemberVO;

@Mapper
public interface MemberMapper {

	/**
	 * 목록 개수 조회
	 * @param wpSeq
	 * @param memberName
	 * @param memberPhone
	 * @param memberSex
	 * @param activeYn
	 * @return
	 */
	Integer getListCnt(@Param("wpSeq") int wpSeq
			, @Param("memberName") String memberName
			, @Param("memberPhone") String memberPhone
			, @Param("memberSex") String memberSex
			, @Param("activeYn") String activeYn);

	/**
	 * 목록 조회
	 * @param wpSeq
	 * @param memberName
	 * @param memberPhone
	 * @param memberSex
	 * @param activeYn
	 * @return
	 */
	List<MemberVO> selectList(@Param("wpSeq") int wpSeq
			, @Param("memberName") String memberName
			, @Param("memberPhone") String memberPhone
			, @Param("memberSex") String memberSex
			, @Param("activeYn") String activeYn
			, @Param("currentPageNo") int currentPageNo
			, @Param("recordCountPerPage") int recordCountPerPage);

	/**
	 * 휴대폰 뒷자리로 회원 조회
	 * @param lastPhoneNumber
	 * @return
	 */
	List<MemberVO> selectPhoneNumber(@Param("lastPhoneNumber") String lastPhoneNumber);

	/**
	 * 이번주 새로 등록된 회원 조회
	 * @return
	 */
	List<MemberVO> selectWeekList();
}

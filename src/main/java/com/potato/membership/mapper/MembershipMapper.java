package com.potato.membership.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.potato.membership.vo.MembershipVO;

@Mapper
public interface MembershipMapper {

	/**
	 * 목록 개수 조회
	 * @param membershipName
	 * @param membershipPeriod
	 * @return
	 */
	Integer getListCnt(@Param("wpSeq") int wpSeq
			, @Param("membershipName") String membershipName
			, @Param("membershipPeriod") String membershipPeriod);

	/**
	 * 목록 조회
	 * @param membershipName
	 * @param membershipPeriod
	 * @param currentPageNo
	 * @param recordCountPerPage
	 * @return
	 */
	List<MembershipVO> selectList(@Param("wpSeq") int wpSeq
			, @Param("membershipName") String membershipName
			, @Param("membershipPeriod") String membershipPeriod
			, @Param("currentPageNo") int currentPageNo
			, @Param("recordCountPerPage") int recordCountPerPage);
}

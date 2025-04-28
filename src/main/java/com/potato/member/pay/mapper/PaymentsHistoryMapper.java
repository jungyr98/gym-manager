package com.potato.member.pay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.potato.member.pay.vo.PaymentsHistoryVO;

@Mapper
public interface PaymentsHistoryMapper {

	/**
	 * 목록 개수 조회
	 * @param phName
	 * @param memberName
	 * @param regDate
	 * @return
	 */
	Integer getListCnt(
			  @Param("phName") String phName
			, @Param("memberName") String memberName
			, @Param("regDate") String regDate);

	/**
	 * 목록 조회
	 * @param phName
	 * @param memberName
	 * @param regDate
	 * @param currentPageNo
	 * @param recordCountPerPage
	 * @return
	 */
	List<PaymentsHistoryVO> selectList(
			  @Param("phName") String phName
			, @Param("memberName") String memberName
			, @Param("regDate") String regDate
			, @Param("currentPageNo") int currentPageNo
			, @Param("recordCountPerPage") int recordCountPerPage);

	/**
	 * 요일별 결제 내역
	 * @return
	 */
	List<PaymentsHistoryVO> selectWeekList();
}

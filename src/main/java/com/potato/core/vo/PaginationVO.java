package com.potato.core.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PaginationVO {

	@Schema(description="현재 페이지 번호")
	private int currentPageNo;

	@Schema(description="페이지당 보여주는 레코드 수")
	private int recordCountPerPage;

	@Schema(description="전체 개수")
	private int totalCnt;

}

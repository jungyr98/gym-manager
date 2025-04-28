package com.potato.core.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseListVO<T> extends PaginationVO {

	private List<T> list;

	public ResponseListVO(List<T> list, int totalCnt) {
		super.setTotalCnt(totalCnt);
		this.list = list;
	}

}

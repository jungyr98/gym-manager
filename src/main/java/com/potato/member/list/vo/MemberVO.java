package com.potato.member.list.vo;

import com.potato.core.entity.WorkplaceMember;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MemberVO extends WorkplaceMember {

	private String startDate;
	private String endDate;

}

package com.potato.message.send.vo;

import java.util.List;

import com.potato.member.list.vo.MemberVO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MessageSendVO {

	private String from;
	private List<MemberVO> toList;
	private String text;

}

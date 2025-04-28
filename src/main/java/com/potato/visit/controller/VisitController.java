package com.potato.visit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potato.core.entity.VisitHistory;
import com.potato.core.enums.ApiCodeEnum;
import com.potato.core.vo.ResponseJson;
import com.potato.core.vo.ResponseListVO;
import com.potato.member.list.vo.MemberVO;
import com.potato.visit.service.VisitService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class VisitController {

	private final VisitService visitService;

	@Operation(summary="방문 회원 조회")
	@GetMapping("/api/v1/visit")
	public ResponseEntity<ResponseJson<ResponseListVO<MemberVO>>> selectMember(
			@Parameter(name="lastPhoneNumber") @RequestParam String lastPhoneNumber) {

		return ResponseEntity.ok(ResponseJson.<ResponseListVO<MemberVO>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(visitService.selectMember(lastPhoneNumber))
				.build());
	}

	@Operation(summary="회원별 방문 기록")
	@GetMapping("/api/v1/visit/{memberSeq}")
	public ResponseEntity<ResponseJson<ResponseListVO<VisitHistory>>> selectMyVisitList(@PathVariable int memberSeq) {

		return ResponseEntity.ok(ResponseJson.<ResponseListVO<VisitHistory>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(visitService.selectMyVisitList(memberSeq))
				.build());
	}

}

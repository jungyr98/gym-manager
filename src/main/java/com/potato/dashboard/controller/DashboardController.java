package com.potato.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.potato.calendar.service.CalendarService;
import com.potato.calendar.vo.CalendarVO;
import com.potato.core.enums.ApiCodeEnum;
import com.potato.core.vo.ResponseJson;
import com.potato.core.vo.ResponseListVO;
import com.potato.member.list.service.MemberService;
import com.potato.member.list.vo.MemberVO;
import com.potato.member.pay.service.PaymentsHistoryService;
import com.potato.member.pay.vo.PaymentsHistoryVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "대시보드 API", description = "컨트롤러에 대한 설명입니다.")
public class DashboardController {

	private final PaymentsHistoryService paymentsHistoryService;
	private final MemberService memberService;
	private final CalendarService calendarService;

	@Operation(summary="이번주 결제 내역")
	@GetMapping("/api/v1/dashboard/payWeek")
	public ResponseEntity<ResponseJson<ResponseListVO<PaymentsHistoryVO>>> selectWeekList() {

		return ResponseEntity.ok(ResponseJson.<ResponseListVO<PaymentsHistoryVO>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(paymentsHistoryService.selectWeekList())
				.build());
	}

	@Operation(summary="이번주 새로 등록된 회원 조회")
	@GetMapping("/api/v1/dashboard/memberWeek")
	public ResponseEntity<ResponseJson<ResponseListVO<MemberVO>>> selectMemberWeekList() {

		return ResponseEntity.ok(ResponseJson.<ResponseListVO<MemberVO>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(memberService.selectWeekList())
				.build());
	}

	@Operation(summary="이번주 일정 목록 조회")
	@GetMapping("/api/v1/dashboard/calendarWeek")
	public ResponseEntity<ResponseJson<ResponseListVO<CalendarVO>>> selectCalendarVO() {

		return ResponseEntity.ok(ResponseJson.<ResponseListVO<CalendarVO>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(calendarService.selectWeekList())
				.build());
	}

}

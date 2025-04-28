package com.potato.chart.sales.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potato.chart.sales.service.SalesChartService;
import com.potato.chart.sales.vo.SalesChartVO;
import com.potato.core.enums.ApiCodeEnum;
import com.potato.core.vo.ResponseJson;
import com.potato.core.vo.ResponseListVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "매출 통계 API", description = "컨트롤러에 대한 설명입니다.")
public class SalesChartController {

	private final SalesChartService salesChartService;

	@Operation(summary="년도별 월 매출 통계")
	@GetMapping("/api/v1/chart/sales/month")
	public ResponseEntity<ResponseJson<ResponseListVO<SalesChartVO>>> selectMonthList(
			@Parameter(name="year") @RequestParam String year) {

		return ResponseEntity.ok(ResponseJson.<ResponseListVO<SalesChartVO>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(salesChartService.selectMonthList(year))
				.build());
	}

	@Operation(summary="회원권별 매출 통계")
	@GetMapping("/api/v1/chart/sales/membership")
	public ResponseEntity<ResponseJson<ResponseListVO<SalesChartVO>>> selectMembershipList(
			@Parameter(name="year") @RequestParam String year) {

		return ResponseEntity.ok(ResponseJson.<ResponseListVO<SalesChartVO>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("ApiCodeEnum.SUCCESS.getNumber()")
				.result(salesChartService.selectMembershipList(year))
				.build());
	}

	@Operation(summary="연령별 매출 통계")
	@GetMapping("/api/v1/chart/sales/age")
	public ResponseEntity<ResponseJson<ResponseListVO<SalesChartVO>>> selectAgeList(
			@Parameter(name="year") @RequestParam String year) {

		return ResponseEntity.ok(ResponseJson.<ResponseListVO<SalesChartVO>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("ApiCodeEnum.SUCCESS.getNumber()")
				.result(salesChartService.selectAgeList(year))
				.build());
	}

}

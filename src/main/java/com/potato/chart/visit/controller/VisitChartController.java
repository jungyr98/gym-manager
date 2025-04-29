package com.potato.chart.visit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.potato.chart.visit.service.VisitChartService;
import com.potato.chart.visit.vo.VisitChartVO;
import com.potato.core.enums.ApiCodeEnum;
import com.potato.core.vo.ResponseJson;
import com.potato.core.vo.ResponseListVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "방문 통계 API", description = "컨트롤러에 대한 설명입니다.")
public class VisitChartController {

	private final VisitChartService visitChartService;

	@Operation(summary="요일별 방문 통계")
	@GetMapping("/api/v1/chart/visit/day")
	public ResponseEntity<ResponseJson<ResponseListVO<VisitChartVO>>> selectList() {

		return ResponseEntity.ok(ResponseJson.<ResponseListVO<VisitChartVO>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(visitChartService.selectList())
				.build());
	}

}

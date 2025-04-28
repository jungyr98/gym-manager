package com.potato.calendar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potato.calendar.service.CalendarService;
import com.potato.calendar.vo.CalendarVO;
import com.potato.core.enums.ApiCodeEnum;
import com.potato.core.vo.ResponseJson;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "일정 관리 API", description = "컨트롤러에 대한 설명입니다.")
public class CalendarController {

	private final CalendarService calendarService;

	@Operation(summary="일정 목록 조회하기", description="년도/월별 일정 데이터 조회")
	@GetMapping("/api/v1/calendar")
	public ResponseEntity<ResponseJson<List<CalendarVO>>> selectList(
			@Parameter(name="wpSeq", description="사업장") @RequestParam int wpSeq
			,@Parameter(description="검색 년도") @RequestParam(required=false) String searchYear
			,@Parameter(description="검색 월") @RequestParam(required=false) String searchMonth) {

		return ResponseEntity.ok(ResponseJson.<List<CalendarVO>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(calendarService.selectList(wpSeq))
				.build());
	}

	@Operation(summary="일정 상세 조회하기", description="")
	@GetMapping("/api/v1/calendar/{clSeq}")
	public ResponseEntity<ResponseJson<CalendarVO>> detail(@PathVariable int clSeq) {
		return ResponseEntity.ok(ResponseJson.<CalendarVO>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(calendarService.selectDetail(clSeq))
				.build());
	}

	@Operation(summary="일정 등록하기", description="")
	@PostMapping("/api/v1/calendar")
	public ResponseEntity<ResponseJson<Object>> insert(@RequestBody CalendarVO calndarVO) {
		calendarService.insert(calndarVO);
		return ResponseEntity.ok(ResponseJson.<Object>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(null)
				.build());
	}

	@Operation(summary="일정 수정하기", description="")
	@PutMapping("/api/v1/calendar")
	public ResponseEntity<Object> update(@RequestBody CalendarVO calndarVO) {
		calendarService.update(calndarVO);
		return ResponseEntity.ok(ResponseJson.<Object>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(null)
				.build());
	}

	@Operation(summary="일정 삭제하기", description="")
	@DeleteMapping("/api/v1/calendar")
	public ResponseEntity<Object> delete(@RequestBody CalendarVO calndarVO) {
		calendarService.delete(calndarVO.getClSeq());
		return ResponseEntity.ok(ResponseJson.<Object>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(null)
				.build());
	}

}

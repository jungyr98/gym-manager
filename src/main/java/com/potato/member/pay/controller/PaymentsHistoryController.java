package com.potato.member.pay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potato.core.enums.ApiCodeEnum;
import com.potato.core.vo.ResponseJson;
import com.potato.core.vo.ResponseListVO;
import com.potato.member.pay.service.PaymentsHistoryService;
import com.potato.member.pay.vo.PaymentsHistoryVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@Tag(name="결제 내역 관리 API", description="컨트롤러에 대한 설명입니다.")
public class PaymentsHistoryController {

	private final PaymentsHistoryService paymentsHistoryService;

	@Operation(summary="결제 내역 목록 조회하기")
	@GetMapping("/api/v1/member/pay")
	public ResponseEntity<ResponseJson<ResponseListVO<PaymentsHistoryVO>>> selectList(
			@Parameter(name="phName", description="결제자 명") @RequestParam(required=false) String phName
			, @Parameter(name="memberName", description="회원 명") @RequestParam(required=false) String memberName
			, @Parameter(name="regDate", description="결제 일자") @RequestParam(required=false) String regDate
			, @Parameter(name="currentPageNo", description="조회할 페이지 번호") @RequestParam(required=false) int currentPageNo
			, @Parameter(name="recordCountPerPage", description="조회할 페이지당 데이터 개수") @RequestParam(required=false) int recordCountPerPage) {

		return ResponseEntity.ok(ResponseJson.<ResponseListVO<PaymentsHistoryVO>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(paymentsHistoryService.selectList(phName, memberName, regDate, currentPageNo, recordCountPerPage))
				.build());
	}

	@Operation(summary="결제 내역 상세 조회하기")
	@GetMapping("/api/v1/member/pay/{phSeq}")
	public ResponseEntity<ResponseJson<PaymentsHistoryVO>> selectDetail(@PathVariable int phSeq) {

		return ResponseEntity.ok(ResponseJson.<PaymentsHistoryVO>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(paymentsHistoryService.selectDetail(phSeq))
				.build());
	}

	@Operation(summary="결제 내역 등록하기")
	@PostMapping("/api/v1/member/pay")
	public ResponseEntity<ResponseJson<Object>> insert(@RequestBody PaymentsHistoryVO paymentsHistoryVO) {

		paymentsHistoryService.insert(paymentsHistoryVO);
		return ResponseEntity.ok(ResponseJson.builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(null)
				.build());
	}

	@Operation(summary="결제 내역 수정하기")
	@PutMapping("/api/v1/member/pay")
	public ResponseEntity<ResponseJson<Object>> update(@RequestBody PaymentsHistoryVO paymentsHistoryVO) {

		paymentsHistoryService.update(paymentsHistoryVO);
		return ResponseEntity.ok(ResponseJson.builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("KO")
				.result(null)
				.build());
	}

	@Operation(summary="결제 내역 삭제하기")
	@DeleteMapping("/api/v1/member/pay")
	public ResponseEntity<ResponseJson<Object>> delete(@RequestBody PaymentsHistoryVO paymentsHistoryVO) {

		paymentsHistoryService.delete(paymentsHistoryVO);
		return ResponseEntity.ok(ResponseJson.builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(null)
				.build());
	}

	@Operation(summary="회원별 결제 내역")
	@GetMapping("/api/v1/member/pay/history/{memberSeq}")
	public ResponseEntity<ResponseJson<ResponseListVO<PaymentsHistoryVO>>> selectMyPayHistoryList(@PathVariable int memberSeq) {

		return ResponseEntity.ok(ResponseJson.<ResponseListVO<PaymentsHistoryVO>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(paymentsHistoryService.selectMyPayHistoryList(memberSeq))
				.build());
	}

}

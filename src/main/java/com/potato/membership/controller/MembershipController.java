package com.potato.membership.controller;


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
import com.potato.membership.service.MembershipService;
import com.potato.membership.vo.MembershipVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "회원권 관리 API", description = "컨트롤러에 대한 설명입니다.")
public class MembershipController {

	private final MembershipService membershipService;

	@Operation(summary="회원권 목록 조회하기")
	@GetMapping("/api/v1/membership")
	public ResponseEntity<ResponseJson<ResponseListVO<MembershipVO>>> selectList(
			@Parameter(name="wpSeq", description="사업장") @RequestParam int wpSeq
			, @Parameter(name="membershipName", description="회원권 명") @RequestParam(required=false) String membershipName
			, @Parameter(name="membershipPeriod", description="회원권 기간") @RequestParam(required=false) String membershipPeriod
			, @Parameter(name="currentPageNo", description="조회할 페이지 번호") @RequestParam(required=false) int currentPageNo
			, @Parameter(name="recordCountPerPage", description="조회할 페이지당 데이터 개수") @RequestParam(required=false) int recordCountPerPage) {

		return ResponseEntity.ok(ResponseJson.<ResponseListVO<MembershipVO>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(membershipService.selectList(wpSeq, membershipName, membershipPeriod, currentPageNo, recordCountPerPage))
				.build());
	}

	@Operation(summary="회원권 상세 조회하기")
	@GetMapping("/api/v1/membership/{membershipSeq}")
	public ResponseEntity<ResponseJson<MembershipVO>> selectDetail(@PathVariable int membershipSeq) {
		return ResponseEntity.ok(ResponseJson.<MembershipVO>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(membershipService.selectDetail(membershipSeq))
				.build());
	}

	@Operation(summary="회원권 등록하기")
	@PostMapping("/api/v1/membership")
	public ResponseEntity<ResponseJson<Object>> insert(@RequestBody MembershipVO membershipVO) {
		membershipService.insert(membershipVO);
		return ResponseEntity.ok(ResponseJson.<Object>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(null)
				.build());
	}

	@Operation(summary="회원권 수정하기")
	@PutMapping("/api/v1/membership")
	public ResponseEntity<ResponseJson<Object>> update(@RequestBody MembershipVO membershipVO) {
		membershipService.update(membershipVO);
		return ResponseEntity.ok(ResponseJson.<Object>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(null)
				.build());
	}

	@Operation(summary="회원권 삭제하기")
	@DeleteMapping("/api/v1/membership")
	public ResponseEntity<ResponseJson<Object>> delete(@RequestBody MembershipVO membershipVO) {
		membershipService.delete(membershipVO);
		return ResponseEntity.ok(ResponseJson.<Object>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(null)
				.build());
	}

}

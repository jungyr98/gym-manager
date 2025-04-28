package com.potato.member.list.controller;

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
import com.potato.member.list.service.MemberService;
import com.potato.member.list.vo.MemberVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "회원 관리 API", description = "컨트롤러에 대한 설명입니다.")
public class MemberController {

	private final MemberService memberService;

	@Operation(summary="회원 목록 조회하기")
	@GetMapping("/api/v1/member/list")
	public ResponseEntity<ResponseJson<ResponseListVO<MemberVO>>> selectList(
			@Parameter(name="wpSeq") @RequestParam int wpSeq
			, @Parameter(name="memberName") @RequestParam(required=false) String memberName
			, @Parameter(name="memberPhone") @RequestParam(required=false) String memberPhone
			, @Parameter(name="memberSex") @RequestParam(required=false) String memberSex
			, @Parameter(name="activeYn") @RequestParam(required=false) String activeYn
			, @Parameter(name="currentPageNo", description="조회할 페이지 번호") @RequestParam(required=false) int currentPageNo
			, @Parameter(name="recordCountPerPage", description="조회할 페이지당 데이터 개수") @RequestParam(required=false) int recordCountPerPage) {

		return ResponseEntity.ok(ResponseJson.<ResponseListVO<MemberVO>>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(memberService.selectList(wpSeq, memberName, memberPhone, memberSex, activeYn, currentPageNo, recordCountPerPage))
				.build());
	}

	@Operation(summary="회원 상세 조회하기")
	@GetMapping("/api/v1/member/list/{memberSeq}")
	public ResponseEntity<ResponseJson<MemberVO>> selectDetail(@PathVariable int memberSeq) {

		return ResponseEntity.ok(ResponseJson.<MemberVO>builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(memberService.selectDetail(memberSeq))
				.build());
	}

	@Operation(summary="회원 등록하기")
	@PostMapping("/api/v1/member/list")
	public ResponseEntity<ResponseJson<Object>> insert(@RequestBody MemberVO memberVO) {
		memberService.insert(memberVO);
		return ResponseEntity.ok(ResponseJson.builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(null)
				.build());
	}

	@Operation(summary="회원 수정하기")
	@PutMapping("/api/v1/member/list")
	public ResponseEntity<ResponseJson<Object>> update(@RequestBody MemberVO memberVO){
		memberService.update(memberVO);
		return ResponseEntity.ok(ResponseJson.builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(null)
				.build());
	}

	@Operation(summary="회원 삭제하기")
	@DeleteMapping("/api/member/v1/list")
	public ResponseEntity<ResponseJson<Object>> delete(@RequestBody MemberVO memberVO) {
		memberService.delete(memberVO);
		return ResponseEntity.ok(ResponseJson.builder()
				.status(ApiCodeEnum.SUCCESS.getNumber())
				.message("OK")
				.result(null)
				.build());
	}


}

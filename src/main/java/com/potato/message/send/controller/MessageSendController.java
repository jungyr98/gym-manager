package com.potato.message.send.controller;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.potato.member.list.vo.MemberVO;
import com.potato.message.send.vo.MessageSendVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Balance;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.model.StorageType;
import net.nurigo.sdk.message.request.MessageListRequest;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.MessageListResponse;
import net.nurigo.sdk.message.response.MultipleDetailMessageSentResponse;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Slf4j
@RestController
@Tag(name = "메세지 전송 API", description = "컨트롤러에 대한 설명입니다.")
public class MessageSendController {

	private DefaultMessageService messageService;

	@Value("${coolsms.API_KEY}")
    private String API_KEY;
	@Value("${coolsms.API_SECRET_KEY}")
    private String API_SECRET_KEY;
	@Value("${coolsms.TEST_FROM_NUMBER}")
    private String TEST_FROM_NUMBER;
	@Value("${file.path.common}")
	private String FILE_PATH;

	@PostConstruct
	public void init() {
	    this.messageService = NurigoApp.INSTANCE.initialize(API_KEY, API_SECRET_KEY, "https://api.coolsms.co.kr");
	}

    @Operation(summary="메시지 조회")
    @GetMapping("/get-message-list")
    public MessageListResponse getMessageList() {
        // 검색 조건이 있는 경우에 MessagListRequest를 초기화 하여 getMessageList 함수에 파라미터로 넣어서 검색할 수 있습니다!.
        // [수신번호와 발신번호는 반드시 -,* 등의 특수문자를 제거한 숫자 형식]
        MessageListRequest request = new MessageListRequest();

        // 검색할 건 수, 값 미지정 시 20건 조회, 최대 500건 까지 설정 가능
        // request.setLimit(1);

        // 조회 후 다음 페이지로 넘어가려면 조회 당시 마지막의 messageId를 입력해주셔야 합니다!
        // request.setStartKey("메시지 ID");

        // request.setTo("검색할 수신번호");
        // request.setFrom("검색할 발신번호");

        // 메시지 상태 검색(MessageStatusType) PENDING: 대기 건 SENDING: 발송 중 COMPLETE: 발송 완료 FAILED: 발송 실패
        /*
        request.setStatus(MessageStatusType.PENDING);
        request.setStatus(MessageStatusType.SENDING);
        request.setStatus(MessageStatusType.COMPLETE);
        request.setStatus(MessageStatusType.FAILED);
        */

        // request.setMessageId("검색할 메시지 ID");

        // 검색할 메시지 목록
        /*
        ArrayList<String> messageIds = new ArrayList<>();
        messageIds.add("검색할 메시지 ID");
        request.setMessageIds(messageIds);
         */

        // 조회 할 메시지 유형 검색, 유형에 대한 값은 아래 내용을 참고해주세요!
        // SMS: 단문
        // LMS: 장문
        // MMS: 사진문자
        // ATA: 알림톡
        // CTA: 친구톡
        // CTI: 이미지 친구톡
        // NSA: 네이버 스마트알림
        // RCS_SMS: RCS 단문
        // RCS_LMS: RCS 장문
        // RCS_MMS: RCS 사진문자
        // RCS_TPL: RCS 템플릿문자
        // request.setType("조회 할 메시지 유형");

        MessageListResponse response = this.messageService.getMessageList(request);

        return response;
    }

    @Operation(summary="단일 메세지 발송")
    @PostMapping("/api/v1/message/send/send-one")
    public SingleMessageSentResponse sendOne(@RequestBody MessageSendVO messageSendVO) {

    	// 1. 메세지 객채 생성 [발신번호|수신번호|메세지내용]
    	// 발신/수신 번호는 반드시 -,* 등의 특수문자를 제거한 숫자 형식
    	// 한글 45자, 영자 90자 이하 입력되면 자동으로 SMS 타입의 메시지
        Message message = new Message();
        message.setFrom(TEST_FROM_NUMBER);
        message.setTo(messageSendVO.getToList().get(0).getMemberPhone());
        message.setText(messageSendVO.getText());

        // 2. 단건 전송 처리
        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));

        return response;
    }

    @Operation(summary="MMS 발송", description="단건/다건 발송 상관없이 이용 가능")
    @PostMapping("/api/v1/message/send/send-mms")
    public SingleMessageSentResponse sendMmsByResourcePath(@RequestParam String text, @RequestParam List<String> toList
    		, @RequestParam MultipartFile file) throws JsonProcessingException, IOException {

    	// 1-1 MultipartFile 파일 객체로 생성
        File newFile = new File(FILE_PATH + file.getOriginalFilename());
        // 1-2. 파일 저장 [FileNotFoundException] 방지
        file.transferTo(newFile);
        // 1-3. 쿨에스엠에스에 파일 업로드 처리
        String imageId = this.messageService.uploadFile(newFile, StorageType.MMS, null);

        // 2. 메세지 객체 리스트 생성
        ArrayList<Message> messageList = new ArrayList<>();

        // 3. 전송 대상자 목록에 따른 반복문 처리
        for(String to : toList) {
        	// 3-1. 정규식으로 숫자만 추출
        	String onlyNumbers = to.replaceAll("\\D+", "");
        	// 3-2. 메세지 객채 생성 [발신번호|수신번호|메세지내용|이미지파일]
            Message message = new Message();
            message.setFrom(TEST_FROM_NUMBER);
        	message.setTo(onlyNumbers);
            message.setText(text);
            message.setImageId(imageId);

            messageList.add(message);
        }

        try {
        	if(messageList.size() > 1) {
        		this.messageService.send(messageList, false, true);
        	} else {
        		this.messageService.sendOne(new SingleMessageSendingRequest(messageList.get(0)));
        	}
        } catch (NurigoMessageNotReceivedException exception) {
            log.error(exception.getMessage(), exception.getFailedMessageList());
        } catch (Exception exception) {
        	log.error(exception.getMessage());
        }

        return null;
    }

    @Operation(summary="여러 메세지 발송", description="한 번 실행으로 최대 10,000건 까지 발송 가능")
    @PostMapping("/api/v1/message/send/send-many")
    public MultipleDetailMessageSentResponse sendMany(@RequestBody MessageSendVO messageSendVO) {

    	// 1. 메세지 객체 리스트 생성
        ArrayList<Message> messageList = new ArrayList<>();

        for (MemberVO memberVO : messageSendVO.getToList()) {
        	// 2. 메세지 객채 생성 [발신번호|수신번호|메세지내용]
        	// 발신/수신 번호는 반드시 -,* 등의 특수문자를 제거한 숫자 형식
            Message message = new Message();
            message.setFrom(TEST_FROM_NUMBER);
            message.setTo(memberVO.getMemberPhone());
            message.setText(messageSendVO.getText());

            // 2. 전송 대상자 별 메세지 커스텀 하기
            /*
            HashMap<String, String> map = new HashMap<>();
            map.put("${name}", memberVO.getMemberName());
            message.setCustomFields(map);
            */

            messageList.add(message);
        }

        try {
            // [메세지데이터|중복수신 허용여부|messageList 리턴여부]
            MultipleDetailMessageSentResponse response = this.messageService.send(messageList, false, true);

            return response;
        } catch (NurigoMessageNotReceivedException exception) {
            log.error(exception.getMessage(), exception.getFailedMessageList());
        } catch (Exception exception) {
        	log.error(exception.getMessage());
        }
        return null;
    }

    @Operation(summary="예약 메세지 발송")
    @PostMapping("/send-scheduled-messages")
    public MultipleDetailMessageSentResponse sendScheduledMessages() {
        ArrayList<Message> messageList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Message message = new Message();
            message.setFrom("발신번호 입력");
            message.setTo("수신번호 입력");
            message.setText("한글 45자, 영자 90자 이하 입력되면 자동으로 SMS타입의 메시지가 추가됩니다." + i);

            messageList.add(message);
        }

        try {
            // 과거 시간으로 예약 발송 진행할 경우, 즉시 발송처리
            LocalDateTime localDateTime = LocalDateTime.parse("2022-11-26 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(localDateTime);
            Instant instant = localDateTime.toInstant(zoneOffset);

            // Message 단일 객체만 넣어도 동작
            MultipleDetailMessageSentResponse response = this.messageService.send(messageList, instant);

            // 중복 수신번호 허용 시 싶으실 경우 아래 코드로 대체
            // MultipleDetailMessageSentResponse response = this.messageService.send(messageList, instant, true);

            System.out.println(response);

            return response;
        } catch (NurigoMessageNotReceivedException exception) {
        	log.error(exception.getMessage(), exception.getFailedMessageList());
        } catch (Exception exception) {
        	log.error(exception.getMessage());
        }
        return null;
    }

    @Operation(summary="잔액 조회")
    @GetMapping("/get-balance")
    public Balance getBalance() {
        Balance balance = this.messageService.getBalance();

        return balance;
    }

}

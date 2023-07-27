package com.v3.furry_friend_chat_cud.controller;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.v3.furry_friend_chat_cud.common.dto.PageRequestDTO;
import com.v3.furry_friend_chat_cud.common.dto.PageResponseDTO;
import com.v3.furry_friend_chat_cud.common.response.ApiResponse;
import com.v3.furry_friend_chat_cud.dto.ChatMessageRequestDTO;
import com.v3.furry_friend_chat_cud.dto.ChatMessageResponseDTO;
import com.v3.furry_friend_chat_cud.dto.ReadMessageRequest;
import com.v3.furry_friend_chat_cud.service.ChatMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chats/{chat_room_id}")
    @SendTo("/sub/chats/{chat_room_id}")
    public ApiResponse<ChatMessageResponseDTO> chatting(ChatMessageRequestDTO chatMessageRequestDTO, @DestinationVariable(value = "chat_room_id") Long chat_room_id, SimpMessageHeaderAccessor headerAccessor) {

        // Token 추출
        String accessToken = headerAccessor.getFirstNativeHeader("Authorization");
        try {

            ChatMessageResponseDTO chatMessageResponseDTO = chatMessageService.saveChatMessage(chat_room_id, chatMessageRequestDTO, accessToken);

            return ApiResponse.success("success", chatMessageResponseDTO);
        }catch (Exception e){

            log.error("메시지 전송 실패: " + e.getMessage(), e);
            return ApiResponse.fail(400, "메시지 전송 실패: " + e.getMessage());
        }
    }

    @MessageMapping("/chats/read")
    public void readMessage(ReadMessageRequest request) {

        Long chatRoomId = request.getChatRoomId();

        try {

            // DB에 메시지를 읽음 상태로 업데이트하는 로직을 추가합니다.
            chatMessageService.checkRead(request.getMessageId());

            // 해당 채팅방을 구독 중인 다른 사용자들에게 메시지를 읽었다는 알림을 보냅니다.
            messagingTemplate.convertAndSend("/sub/chats/" + chatRoomId, "success: " + request.getMessageId());
        }catch (Exception e){

            log.error("오류 발생: " + e.getMessage(), e);
            messagingTemplate.convertAndSend("/sub/chats/" + chatRoomId, "오류 발생: " + e.getMessage());
        }


    }

    // 채팅방 메시지 조회
    @GetMapping("/chats/{chatRoomId}")
    public ApiResponse<PageResponseDTO> getMessages(@PathVariable("chatRoomId") Long chatRoomId, @RequestHeader(value = "Authorization") String accessToken, PageRequestDTO pageRequestDTO, @RequestParam("time") LocalDateTime time) {

        try {
            PageResponseDTO pageResponseDTO = chatMessageService.readChatMessage(pageRequestDTO, chatRoomId, accessToken, time);
            return ApiResponse.success("success", pageResponseDTO);
        }catch (Exception e){
            log.error("" + e.getMessage(), e);
            return ApiResponse.fail(400, "" + e.getMessage());
        }
    }

    // @PatchMapping("/chats")
    // public ApiResponse readMessage(@RequestBody List<Long> chatMessagesIdList){
    //     try {
    //
    //         chatMessageService.checkRead(chatMessagesIdList);
    //         return ApiResponse.success("success");
    //     }catch (Exception e){
    //         log.error("에러 발생: " + e.getMessage(), e);
    //         return ApiResponse.fail(400, "에러 발생: " + e.getMessage());
    //     }
    //
    // }
}

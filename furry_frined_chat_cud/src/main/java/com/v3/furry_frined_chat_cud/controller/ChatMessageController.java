package com.v3.furry_frined_chat_cud.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.v3.furry_frined_chat_cud.common.dto.PageRequestDTO;
import com.v3.furry_frined_chat_cud.common.dto.PageResponseDTO;
import com.v3.furry_frined_chat_cud.common.response.ApiResponse;
import com.v3.furry_frined_chat_cud.dto.ChatMessageRequestDTO;
import com.v3.furry_frined_chat_cud.dto.ChatMessageResponseDTO;
import com.v3.furry_frined_chat_cud.service.ChatMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chats/{chat_room_id}")
    @SendTo("/chats/{chat_room_id}")
    public ApiResponse<ChatMessageResponseDTO> chatting(ChatMessageRequestDTO chatMessageRequestDTO, @DestinationVariable(value = "chat_room_id") Long chat_room_id, SimpMessageHeaderAccessor headerAccessor) {

        // Token 추출
        String accessToken = headerAccessor.getFirstNativeHeader("Authorization");
        log.info("{roomNo : {}, request : {}, token : {}}", chat_room_id, chatMessageRequestDTO, accessToken);
        try {

            ChatMessageResponseDTO chatMessageResponseDTO = chatMessageService.saveChatMessage(chat_room_id,
                chatMessageRequestDTO, accessToken);
            return ApiResponse.success("success", chatMessageResponseDTO);
        }catch (Exception e){

            log.error("메시지 전송 실패: " + e.getMessage(), e);
            return ApiResponse.fail(400, "메시지 전송 실패: " + e.getMessage());
        }
     }

     @GetMapping("/chats/{chatRoomId}")
    public ApiResponse<PageResponseDTO> getMessages(@PathVariable("chatRoomId") Long chatRoomId, @RequestHeader(value = "Authorization") String accessToken, PageRequestDTO pageRequestDTO) {

        try {
            PageResponseDTO pageResponseDTO = chatMessageService.readChatMessage(pageRequestDTO, chatRoomId, accessToken);
            return ApiResponse.success("success", pageResponseDTO);
        }catch (Exception e){
            log.error("" + e.getMessage(), e);
            return ApiResponse.fail(400, "" + e.getMessage());
        }
     }
}

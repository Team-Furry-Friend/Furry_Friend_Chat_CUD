package com.v3.furry_frined_chat_cud.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v3.furry_frined_chat_cud.common.response.ApiResponse;
import com.v3.furry_frined_chat_cud.dto.ChatParticipantsResponseDTO;
import com.v3.furry_frined_chat_cud.dto.ChatRoomRequestDTO;
import com.v3.furry_frined_chat_cud.service.ChatRoomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/chats")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    // 채팅방 목록 조회
    // NoSQL로 처리 R에서 처리

    // 채팅방 생성
    @PostMapping("")
    public ApiResponse<ChatParticipantsResponseDTO> startChat(@RequestBody ChatRoomRequestDTO chatRoomRequestDTO){

        try {
            ChatParticipantsResponseDTO chatParticipantsResponseDTO = chatRoomService.createChatRoom(chatRoomRequestDTO);
            return ApiResponse.success("success", chatParticipantsResponseDTO);
        }catch (Exception e){
            log.error("에러 발생: " + e.getMessage(), e);
            return ApiResponse.fail(400, "에러 발생: " + e.getMessage());
        }
    }

    // 채팅방 삭제
    @DeleteMapping("/{chat_room_id}")
    public ApiResponse deleteChat(@PathVariable("chat_room_id") Long chat_room_id, @RequestHeader(value = "Authorization") String accessToken){

        try {
            chatRoomService.deleteChatRoom(accessToken, chat_room_id);
            return ApiResponse.success("success");
        }catch (Exception e){
            log.error("에러 발생: " + e.getMessage(), e);
            return ApiResponse.fail(400, "에러 발생: " + e.getMessage());
        }
    }

}

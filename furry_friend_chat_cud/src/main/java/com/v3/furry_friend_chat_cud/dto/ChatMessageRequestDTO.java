package com.v3.furry_friend_chat_cud.dto;

import com.v3.furry_friend_chat_cud.common.dto.JwtResponse;
import com.v3.furry_friend_chat_cud.entity.ChatMessage;
import com.v3.furry_friend_chat_cud.entity.ChatRoom;

public class ChatMessageRequestDTO {

    private String content;

    public String getContent() {
        return content;
    }

    public ChatMessage dtoToEntity(ChatMessageRequestDTO chatMessageRequestDTO, JwtResponse jwtResponse, ChatRoom chatRoom){

        return ChatMessage.builder()
            .chatMessageContent(chatMessageRequestDTO.getContent())
            .chatMessageDel(false)
            .chatMessageSenderId(jwtResponse.getMemberId())
            .chatMessageSerderName(jwtResponse.getMemberName())
            .chatMessageRead(false)
            .chatRoom(chatRoom)
            .build();
    }

}

package com.v3.furry_frined_chat_cud.dto;

import com.v3.furry_frined_chat_cud.entity.ChatRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomResponseDTO {

    private Long chatRoomId;

    private String chatName;

    private Long chatCreator;

    private boolean chatDel;
}

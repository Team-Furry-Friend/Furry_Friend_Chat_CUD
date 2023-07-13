package com.v3.furry_frined_chat_cud.dto;

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

    public ChatRoomResponseDTO entityToDTO() {
        return ChatRoomResponseDTO.builder()
            .chatRoomId(this.getChatRoomId())
            .chatName(this.getChatName())
            .chatCreator(this.getChatCreator())
            .chatDel(this.isChatDel())
            .build();
    }
}

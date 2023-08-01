package com.v3.furry_friend_chat_cud.dto;

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

    private Long chatCreatorId;
    private String chatCreatorName;

    private boolean chatDel;
}

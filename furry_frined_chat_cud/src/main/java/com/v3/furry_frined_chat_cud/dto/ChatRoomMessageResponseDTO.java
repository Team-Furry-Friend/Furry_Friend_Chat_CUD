package com.v3.furry_frined_chat_cud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatRoomMessageResponseDTO {

    private ChatMessageResponseDTO chatMessageResponseDTO;
    private ChatParticipantsResponseDTO chatParticipantsResponseDTO;
}

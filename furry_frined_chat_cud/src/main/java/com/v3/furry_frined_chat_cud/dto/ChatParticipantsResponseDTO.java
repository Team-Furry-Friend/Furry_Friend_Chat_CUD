package com.v3.furry_frined_chat_cud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatParticipantsResponseDTO {

    private Long chatParticipantsId;

    private Long chatParticipantsMemberId;

    private String chatParticipantsMemberName;

    private boolean chatParticipantsDel;

    private ChatRoomResponseDTO chatRoomResponseDTO;
}

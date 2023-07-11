package com.v3.furry_frined_chat_cud.dto;

import com.v3.furry_frined_chat_cud.common.dto.JwtRequest;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChatRoomRequestDTO {

    private Long chatParticipantsId;
    private String chatParticipantsName;
    private JwtRequest jwtRequest;
}

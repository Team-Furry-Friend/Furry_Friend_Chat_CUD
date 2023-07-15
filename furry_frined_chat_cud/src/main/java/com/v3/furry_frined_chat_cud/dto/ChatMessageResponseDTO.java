package com.v3.furry_frined_chat_cud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageResponseDTO {

    private Long chatMessageId;

    private Long chatMessageSenderId;
    private String chatMessageSerderName;

    private String chatMessageContent;

    private boolean chatMessageRead;

    private boolean chatMessageDel;
}
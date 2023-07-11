package com.v3.furry_frined_chat_cud.dto;

import lombok.Builder;

@Builder
public class ChatMessageResponseDTO {

    private Long chatMessageId;

    private Long chatMessageSenderId;
    private String chatMessageSerderName;

    private String chatMessageContent;

    private boolean chatMessageRead;

    private boolean chatMessageDel;
}
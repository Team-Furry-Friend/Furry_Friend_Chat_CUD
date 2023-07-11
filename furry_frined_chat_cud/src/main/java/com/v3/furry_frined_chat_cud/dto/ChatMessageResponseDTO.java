package com.v3.furry_frined_chat_cud.dto;

import lombok.Builder;

@Builder
public class ChatMessageResponseDTO {

    private String name;

    public String getName() {
        return name;
    }
}
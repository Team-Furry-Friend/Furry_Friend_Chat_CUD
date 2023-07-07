package com.v3.furry_frined_chat_cud.dto;

public class ChatMessageRequest {

    private String content;

    public ChatMessageRequest() {
    }

    public ChatMessageRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}

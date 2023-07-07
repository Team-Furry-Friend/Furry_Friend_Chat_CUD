package com.v3.furry_frined_chat_cud.dto;

public class ChatMessageResponse {

    private String name;

    public ChatMessageResponse() {
    }

    public ChatMessageResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
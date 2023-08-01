package com.v3.furry_friend_chat_cud.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReadMessageRequest {
    private Long chatRoomId;
    private List<Long> messageId;
}

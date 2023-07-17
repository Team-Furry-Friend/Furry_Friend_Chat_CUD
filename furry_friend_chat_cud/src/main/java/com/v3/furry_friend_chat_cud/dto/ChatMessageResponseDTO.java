package com.v3.furry_friend_chat_cud.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatMessageResponseDTO {

    private Long chatMessageId;

    private Long chatMessageSenderId;
    private String chatMessageSerderName;

    private String chatMessageContent;

    private boolean chatMessageRead;

    private boolean chatMessageDel;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
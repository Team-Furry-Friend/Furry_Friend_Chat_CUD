package com.v3.furry_friend_chat_cud.entity;

import com.v3.furry_friend_chat_cud.common.entity.BaseEntity;
import com.v3.furry_friend_chat_cud.dto.ChatMessageResponseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageId;

    private Long chatMessageSenderId;
    private String chatMessageSerderName;

    private String chatMessageContent;

    private boolean chatMessageRead;

    private boolean chatMessageDel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatRoomId")
    private ChatRoom chatRoom;

    public ChatMessage(String chatMessageContent, boolean chatMessageDel, boolean chatMessageRead, Long chatMessageSenderId, String chatMessageSerderName, ChatRoom chatRoom){
        this.chatMessageContent = chatMessageContent;
        this.chatMessageDel = chatMessageDel;
        this.chatMessageRead = chatMessageRead;
        this.chatMessageSenderId = chatMessageSenderId;
        this.chatMessageSerderName = chatMessageSerderName;
        this.chatRoom = chatRoom;
    }

    public void setChatMessageRead(boolean chatMessageRead){
        this.chatMessageRead = chatMessageRead;
    }

    public ChatMessageResponseDTO entityToDTO(ChatMessage chatMessage){

        return ChatMessageResponseDTO.builder()
            .chatMessageId(chatMessage.getChatMessageId())
            .chatMessageContent(chatMessage.getChatMessageContent())
            .chatMessageSenderId(chatMessage.getChatMessageSenderId())
            .chatMessageSerderName(chatMessage.getChatMessageSerderName())
            .chatMessageDel(chatMessage.isChatMessageDel())
            .chatMessageRead(chatMessage.isChatMessageRead())
            .regDate(chatMessage.getRegDate())
            .modDate(chatMessage.getModDate())
            .build();
    }
}

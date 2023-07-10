package com.v3.furry_frined_chat_cud.entity;

import java.util.ArrayList;
import java.util.List;

import com.v3.furry_frined_chat_cud.common.entity.BaseEntity;
import com.v3.furry_frined_chat_cud.dto.ChatRoomResponseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    private String chatName;

    private Long chatCreator;

    private boolean chatDel;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessages;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatParticipants> chatParticipants;

    public ChatRoom(String chatName, Long chatCreator, boolean chatDel) {
        this.chatName = chatName;
        this.chatCreator = chatCreator;
        this.chatDel = chatDel;
    }

    public void setChatDel(boolean chatDel){
        this.chatDel = chatDel;
    }

    public ChatRoomResponseDTO entityToDTO() {
        return ChatRoomResponseDTO.builder()
            .chatRoomId(this.getChatRoomId())
            .chatName(this.getChatName())
            .chatCreator(this.getChatCreator())
            .chatDel(this.isChatDel())
            .build();
    }
}

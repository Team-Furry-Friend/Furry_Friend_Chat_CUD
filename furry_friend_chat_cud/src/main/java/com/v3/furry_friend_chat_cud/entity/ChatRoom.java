package com.v3.furry_friend_chat_cud.entity;

import java.util.List;

import com.v3.furry_friend_chat_cud.common.entity.BaseEntity;
import com.v3.furry_friend_chat_cud.dto.ChatRoomResponseDTO;

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

    private Long chatCreatorId;
    private String chatCreatorName;

    private boolean chatDel;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessages;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatParticipants> chatParticipants;

    public ChatRoom(String chatName, Long chatCreatorId, String chatCreatorName, boolean chatDel) {
        this.chatName = chatName;
        this.chatCreatorId = chatCreatorId;
        this.chatCreatorName = chatCreatorName;
        this.chatDel = chatDel;
    }

    public void setChatDel(boolean chatDel){
        this.chatDel = chatDel;
    }

    public ChatRoomResponseDTO entityToDTO(ChatRoom chatRoom) {
        return ChatRoomResponseDTO.builder()
            .chatRoomId(chatRoom.getChatRoomId())
            .chatName(chatRoom.getChatName())
            .chatCreatorId(chatRoom.getChatCreatorId())
            .chatCreatorName(chatRoom.getChatCreatorName())
            .chatDel(chatRoom.isChatDel())
            .build();
    }
}

package com.v3.furry_friend_chat_cud.entity;

import com.v3.furry_friend_chat_cud.common.entity.BaseEntity;
import com.v3.furry_friend_chat_cud.dto.ChatParticipantsResponseDTO;

import jakarta.persistence.CascadeType;
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
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatParticipants extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatParticipantsId;

    private Long chatParticipantsMemberId;

    private String chatParticipantsMemberName;

    private boolean chatParticipantsDel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "chatRoomId")
    private ChatRoom chatRoom;

    public ChatParticipants(Long chatParticipantsMemberId, String chatParticipantsMemberName, boolean chatParticipantsDel, ChatRoom chatRoom){
        this.chatParticipantsMemberId = chatParticipantsMemberId;
        this.chatParticipantsMemberName = chatParticipantsMemberName;
        this.chatParticipantsDel = chatParticipantsDel;
        this.chatRoom = chatRoom;
    }

    public ChatParticipantsResponseDTO entityToDTO(ChatParticipants chatParticipants){

        ChatRoom chatRoom = new ChatRoom();

        return ChatParticipantsResponseDTO.builder()
            .chatParticipantsId(chatParticipants.getChatParticipantsId())
            .chatParticipantsMemberId(chatParticipants.getChatParticipantsMemberId())
            .chatParticipantsMemberName(chatParticipants.getChatParticipantsMemberName())
            .chatParticipantsDel(chatParticipants.isChatParticipantsDel())
            .chatRoomResponseDTO(chatRoom.entityToDTO(chatParticipants.getChatRoom()))
            .build();
    }

    public void setChatParticipantsDel(boolean chatParticipantsDel){
        this.chatParticipantsDel = chatParticipantsDel;
    }
}

package com.v3.furry_frined_chat_cud.entity;

import java.util.ArrayList;
import java.util.List;

import com.v3.furry_frined_chat_cud.common.entity.BaseEntity;

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
    private Long chat_room_id;

    private String chat_name;

    private Long chat_creator;

    private boolean chat_del;

    @OneToMany(mappedBy = "chat_room")
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @OneToMany(mappedBy = "chat_room")
    private List<ChatParticipants> chatParticipants = new ArrayList<>();
}

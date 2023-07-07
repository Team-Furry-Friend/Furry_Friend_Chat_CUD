package com.v3.furry_frined_chat_cud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v3.furry_frined_chat_cud.entity.ChatParticipants;

@Repository
public interface ChatParticipantsRepository extends JpaRepository<ChatParticipants, Long> {
}

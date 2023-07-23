package com.v3.furry_friend_chat_cud.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.v3.furry_friend_chat_cud.entity.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    // 페이징을 통해 채팅 내역을 가져옴.
    @Query("select cm from ChatMessage cm "
        + "where cm.chatRoom.chatRoomId = :chatRoomId and cm.chatMessageDel = false "
        + "and cm.regDate <= :time")
    Page<ChatMessage> getChatMessage(Pageable pageable, Long chatRoomId, LocalDateTime time);
}

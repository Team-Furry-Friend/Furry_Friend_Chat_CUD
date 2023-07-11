package com.v3.furry_frined_chat_cud.ChatParticipantsTests;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.v3.furry_frined_chat_cud.entity.ChatParticipants;
import com.v3.furry_frined_chat_cud.entity.ChatRoom;
import com.v3.furry_frined_chat_cud.repository.ChatParticipantsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
@Transactional
public class ChatParticipantsServiceTests {

    @Autowired
    ChatParticipantsRepository chatParticipantsRepository;

    @PersistenceContext
    EntityManager em;

    private void clear() {
        em.flush(); // 1
        em.clear(); // 2
    }

    private ChatRoom createChatRoom() {
        return new ChatRoom("chat_name", 1L, false);
    }
    private ChatParticipants createChatParticipants() {
        return new ChatParticipants(0L, "soo", false, createChatRoom());
    }

    @Test
    @DisplayName("ChatRoom 생성 테스트")
    void createChatRoomTest() throws Exception{

        // Given
        ChatParticipants chatParticipants = createChatParticipants();

        // When
        chatParticipantsRepository.save(chatParticipants);
        clear();
        ChatParticipants result = chatParticipantsRepository.findById(chatParticipants.getChatParticipantsId())
            .orElseThrow(() -> new Exception("ChatParticipants을 찾을 수 없습니다."));

        // Then
        assertThat(chatParticipants.getChatParticipantsId()).isEqualTo(result.getChatParticipantsId());
    }

    @Test
    @DisplayName("ChatParticipants 삭제 테스트")
    void deleteChatRoomTest() throws Exception{

        // Given
        ChatParticipants chatParticipants = createChatParticipants();
        chatParticipantsRepository.save(chatParticipants);

        // When
        ChatParticipants result = chatParticipantsRepository.findById(chatParticipants.getChatParticipantsId())
            .orElseThrow(() -> new Exception("ChatParticipants을 찾을 수 없습니다."));
        result.setChatParticipantsDel(true);
        chatParticipantsRepository.save(result);
        clear();

        ChatParticipants result1 = chatParticipantsRepository.findById(chatParticipants.getChatParticipantsId())
            .orElseThrow(() -> new Exception("ChatParticipants을 찾을 수 없습니다."));

        // Then
        assertThat(result.isChatParticipantsDel()).isEqualTo(result1.isChatParticipantsDel());
    }
}

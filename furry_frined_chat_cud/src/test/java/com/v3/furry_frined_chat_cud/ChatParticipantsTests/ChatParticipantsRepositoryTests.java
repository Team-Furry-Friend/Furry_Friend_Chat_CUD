package com.v3.furry_frined_chat_cud.ChatParticipantsTests;

import static org.assertj.core.api.Assertions.assertThat;

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
public class ChatParticipantsRepositoryTests {

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
    @DisplayName("JPA Save 테스트")
    void createTest() throws Exception{

        // given
        ChatParticipants chatParticipants = createChatParticipants();

        // when
        chatParticipantsRepository.save(chatParticipants);
        clear();

        // then
        ChatParticipants result = chatParticipantsRepository.findById(chatParticipants.getChatParticipantsId())
            .orElseThrow(() -> new Exception("참여자 저장에 실패했습니다."));
        assertThat(result.getChatParticipantsId()).isEqualTo(chatParticipants.getChatParticipantsId());
    }

    @Test
    @DisplayName("JPA Select 테스트")
    void findChatParticipantsTest(){

        // given
        ChatParticipants chatParticipants = createChatParticipants();
        chatParticipantsRepository.save(chatParticipants);
        clear();

        // when
        ChatParticipants result = chatParticipantsRepository.findChatParticipants(chatParticipants.getChatRoom().getChatRoomId(), chatParticipants.getChatParticipantsMemberId());

        // then
        assertThat(chatParticipants.getChatParticipantsMemberId()).isEqualTo(result.getChatParticipantsMemberId());
    }
}

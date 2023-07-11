package com.v3.furry_frined_chat_cud.ChatRoomTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.v3.furry_frined_chat_cud.entity.ChatRoom;
import com.v3.furry_frined_chat_cud.repository.ChatRoomRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ChatRoomRepositoryTests {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @PersistenceContext
    EntityManager em;

    private void clear() {
        em.flush(); // 1
        em.clear(); // 2
    }

    private ChatRoom createChatRoom() {
        return new ChatRoom("chat_name", 1L, false);
    }

    @Test
    @DisplayName("JPA Save 테스트")
    void createTest() throws Exception {
        // given
        ChatRoom chatRoom = createChatRoom();

        // when
        chatRoomRepository.save(chatRoom);
        clear();

        // then
        ChatRoom result = chatRoomRepository.findById(chatRoom.getChatRoomId())
            .orElseThrow(() -> new Exception("ChatRoom을 찾을 수 없습니다."));
        assertThat(result.getChatRoomId()).isEqualTo(chatRoom.getChatRoomId());
    }

    @Test
    @DisplayName("JPA Select 테스트")
    void findChatRoomByChatRoomIdTest() {

        // given
        ChatRoom chatRoom = createChatRoom();
        chatRoomRepository.save(chatRoom);
        clear();

        // when
        ChatRoom result = chatRoomRepository.findChatRoomByChatRoomId(chatRoom.getChatRoomId());

        // then
        assertThat(chatRoom.getChatRoomId()).isEqualTo(result.getChatRoomId());
    }
}

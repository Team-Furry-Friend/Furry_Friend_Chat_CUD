package com.v3.furry_frined_chat_cud.ChatMessageTests;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.v3.furry_frined_chat_cud.entity.ChatMessage;
import com.v3.furry_frined_chat_cud.entity.ChatRoom;
import com.v3.furry_frined_chat_cud.repository.ChatMessageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
@Transactional
public class ChatMessageServiceTests {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @PersistenceContext
    EntityManager em;

    private void clear() {
        em.flush(); // 1
        em.clear(); // 2
    }

    private ChatRoom createChatRoom() {
        return new ChatRoom("chat_name", 1L, false);
    }
    private ChatMessage createChatMessage(){
        return new ChatMessage("Hi!", false, false, 1L, "soo", createChatRoom());
    }

    @Test
    @DisplayName("ChatMessage Save 테스트")
    void createMessageTest() throws Exception{

        // given
        ChatMessage chatMessage = createChatMessage();

        // when
        chatMessageRepository.save(chatMessage);
        clear();
        ChatMessage result = chatMessageRepository.findById(chatMessage.getChatMessageId())
            .orElseThrow(() -> new Exception("메시지 저장에 실패했습니다."));

        // then
        assertThat(result.getChatMessageId()).isEqualTo(chatMessage.getChatMessageId());
    }
}

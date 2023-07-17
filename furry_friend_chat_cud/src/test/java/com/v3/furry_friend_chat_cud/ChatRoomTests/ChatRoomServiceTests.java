package com.v3.furry_friend_chat_cud.ChatRoomTests;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import com.v3.furry_friend_chat_cud.entity.ChatRoom;
import com.v3.furry_friend_chat_cud.repository.ChatRoomRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
@Transactional
public class ChatRoomServiceTests {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @PersistenceContext
    EntityManager em;

    private void clear() {
        em.flush(); // 1
        em.clear(); // 2
    }

    private ChatRoom createChatRoom() {
        return new ChatRoom("{soo} 님이 만드신 채팅방", 1L, false);
    }

    @Test
    @DisplayName("ChatRoom 생성 테스트")
    void createChatRoomTest() throws Exception{

        // Given
        ChatRoom chatRoom = createChatRoom();

        // When
        chatRoomRepository.save(chatRoom);
        clear();
        ChatRoom result = chatRoomRepository.findById(chatRoom.getChatRoomId())
            .orElseThrow(() -> new Exception("ChatRoom을 찾을 수 없습니다."));


        // Then
        assertThat(chatRoom.getChatRoomId()).isEqualTo(result.getChatRoomId());
    }

    @Test
    @DisplayName("ChatRoom 삭제 테스트")
    void deleteChatRoomTest() throws Exception{

        // Given
        ChatRoom chatRoom = createChatRoom();
        chatRoomRepository.save(chatRoom);

        // When
        ChatRoom result = chatRoomRepository.findById(chatRoom.getChatRoomId())
            .orElseThrow(() -> new Exception("ChatRoom을 찾을 수 없습니다."));
        result.setChatDel(true);
        chatRoomRepository.save(result);
        clear();

        ChatRoom result1 = chatRoomRepository.findById(chatRoom.getChatRoomId())
            .orElseThrow(() -> new Exception("ChatRoom을 찾을 수 없습니다."));

        // Then
        assertThat(result.isChatDel()).isEqualTo(result1.isChatDel());
    }
}

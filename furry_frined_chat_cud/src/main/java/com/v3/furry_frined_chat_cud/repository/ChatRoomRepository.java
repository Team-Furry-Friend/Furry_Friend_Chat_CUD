package com.v3.furry_frined_chat_cud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.v3.furry_frined_chat_cud.entity.ChatParticipants;
import com.v3.furry_frined_chat_cud.entity.ChatRoom;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    // 채팅방 조회
    @Query("select cr, cp from ChatRoom cr left outer join ChatParticipants cp on cr.chatRoomId = cp.chatRoom.chatRoomId "
        + "where cr.chatRoomId = :chatRoomId "
        + "group by cr")
    ChatRoom findChatRoomByChatRoomId(Long chatRoomId);



    // 채팅방과 채팅 참여자 및 마지막 채팅 조회(미완성)
    // 둘 중 하나 false일 때 찾는 이유는 갑자기 채팅방이 사라지면 안 되니까
    // @Query("select cr, cp from ChatParticipants cp left outer join ChatRoom cr on cr.chatRoomId = cp.chatRoom.chatRoomId "
    //     + "where (cp.chatParticipantsMember = :memberId "
    //     + "or cr.chatCreator = :memberId) "
    //     + "and cr.chatDel=false "
    //     + "group by cr")
    // List<Object []> findChatRoomByChatParticipantsMember(Long memberId);
}

package com.v3.furry_friend_chat_cud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.v3.furry_friend_chat_cud.entity.ChatRoom;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    // 채팅방 조회
    @Query("select cr, cp from ChatRoom cr left outer join ChatParticipants cp on cr.chatRoomId = cp.chatRoom.chatRoomId "
        + "where cr.chatRoomId = :chatRoomId "
        + "group by cr")
    ChatRoom findChatRoomByChatRoomId(Long chatRoomId);

    // 유저의 모든 채팅방과 채팅 참여자 및 마지막 채팅 조회(미완성)
    @Query("select cm, cp, (select count(cm2) from ChatMessage cm2 where cm2.chatRoom = cr and cm2.chatMessageRead = false and cm2.chatMessageSenderId <> :memberId) from ChatRoom cr left join ChatParticipants cp on cr.chatRoomId = cp.chatRoom.chatRoomId left join ChatMessage cm on cr.chatRoomId = cm.chatRoom.chatRoomId "
        + "where (cp.chatParticipantsMemberId = :memberId or cr.chatCreatorId = :memberId) "
        + "and cr.chatDel=false "
        + "group by cr "
        + "order by cm.regDate")
    List<Object []> findChatRoomByMember(Long memberId);
}

package com.v3.furry_friend_chat_cud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.v3.furry_friend_chat_cud.entity.ChatParticipants;

@Repository
public interface ChatParticipantsRepository extends JpaRepository<ChatParticipants, Long> {

    // 참여자 찾는 쿼리
    @Query("select c from ChatParticipants c "
        + "where c.chatRoom.chatRoomId = :chatRoomId "
        + "and c.chatParticipantsMemberId = :memberId")
    ChatParticipants findChatParticipants(Long chatRoomId, Long memberId);

    // 채팅방과 채팅 참여자 조회
    @Query("select cp from ChatParticipants cp "
        + "where ((cp.chatParticipantsMemberId = :senderId and cp.chatRoom.chatCreatorId = :creatorId) "
        + "or (cp.chatRoom.chatCreatorId = :senderId and cp.chatParticipantsMemberId = :creatorId)) "
        + "and (cp.chatRoom.chatDel=false and cp.chatParticipantsDel=false)")
    ChatParticipants findChatRoomByChatParticipantsMember(Long creatorId, Long senderId);

}

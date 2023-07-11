package com.v3.furry_frined_chat_cud.service;

import org.springframework.stereotype.Service;

import com.v3.furry_frined_chat_cud.common.dto.JwtResponse;
import com.v3.furry_frined_chat_cud.dto.ChatRoomRequestDTO;
import com.v3.furry_frined_chat_cud.entity.ChatParticipants;
import com.v3.furry_frined_chat_cud.entity.ChatRoom;
import com.v3.furry_frined_chat_cud.repository.ChatParticipantsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatParticipantsService {


    private final ChatParticipantsRepository chatParticipantsRepository;

    public void createChatParticipants(ChatRoom chatRoom, ChatRoomRequestDTO chatRoomRequestDTO){

        ChatParticipants chatParticipants = ChatParticipants.builder()
            .chatRoom(chatRoom)
            .chatParticipantsMemberId(chatRoomRequestDTO.getChatParticipantsId())
            .chatParticipantsMemberName(chatRoomRequestDTO.getChatParticipantsName())
            .chatParticipantsDel(false)
            .chatRoom(chatRoom)
            .build();

        chatParticipantsRepository.save(chatParticipants);
    }

    // 참여자 삭제
    public void deleteChatParticipants(Long chatRoomId, JwtResponse jwtResponse){
        
        ChatParticipants chatParticipants = chatParticipantsRepository.findChatParticipants(chatRoomId, jwtResponse.getMemberId());
        chatParticipants.setChatParticipantsDel(true);
        chatParticipantsRepository.save(chatParticipants);
    }
}

package com.v3.furry_frined_chat_cud.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.v3.furry_frined_chat_cud.common.dto.JwtResponse;
import com.v3.furry_frined_chat_cud.common.dto.PageRequestDTO;
import com.v3.furry_frined_chat_cud.common.dto.PageResponseDTO;
import com.v3.furry_frined_chat_cud.common.service.TokenService;
import com.v3.furry_frined_chat_cud.dto.ChatMessageRequestDTO;
import com.v3.furry_frined_chat_cud.dto.ChatMessageResponseDTO;
import com.v3.furry_frined_chat_cud.entity.ChatMessage;
import com.v3.furry_frined_chat_cud.entity.ChatRoom;
import com.v3.furry_frined_chat_cud.repository.ChatMessageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    private final ChatRoomService chatRoomService;

    private final TokenService tokenService;

    // 메시지 저장 메서드
    public ChatMessageResponseDTO saveChatMessage(final Long chatRoomId, final ChatMessageRequestDTO chatMessageRequestDTO, String accessToken) throws Exception{

        try {
            
            JwtResponse jwtResponse = tokenService.getMember(accessToken);
            ChatRoom chatRoom = chatRoomService.findChatRoom(chatRoomId);
            
            // Entity로 변환 후 저장
            ChatMessage chatMessage = chatMessageRequestDTO.dtoToEntity(chatMessageRequestDTO, jwtResponse, chatRoom);
            chatMessageRepository.save(chatMessage);

            // DTO로 변환 후 응답
            return chatMessage.entityToDTO(chatMessage);
        } catch (Exception e){

            log.error("ChatMessageService 오류 발생: " + e.getMessage(), e);
            throw new Exception("ChatMessageService 오류 발생: " + e.getMessage());
        }
    }

    // 페이징을 통해 받아오는 채팅 내역
    public PageResponseDTO<ChatMessageResponseDTO> readChatMessage(PageRequestDTO pageRequestDTO, final Long chatRoomId, final String accessToken) throws Exception {

        try {
            JwtResponse jwtResponse = tokenService.getMember(accessToken);
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("chatMessageId").descending());
            Page<ChatMessage> result = chatMessageRepository.getChatMessage(pageable, chatRoomId);

            Function<ChatMessage, ChatMessageResponseDTO> function = (chatMessage -> {
                if (chatMessage != null){

                    // 요청하는 사용자가 채팅 내역과 관련이 있을 때
                    if (jwtResponse.getMemberId().equals(chatMessage.getChatRoom().getChatParticipants().get(0).getChatParticipantsMemberId()) || jwtResponse.getMemberId().equals(chatMessage.getChatRoom().getChatCreator())){
                        return chatMessage.entityToDTO(chatMessage);
                    }
                }
                throw new NullPointerException("메시지가 존재하지 않습니다.");
            });

            return new PageResponseDTO<>(result.map(function));
        }catch (Exception e){
            log.error("ChatMessageService 채팅 내역 조회 오류 발생: " + e.getMessage(), e);
            throw new Exception("ChatMessageService 채팅 내역 조회 오류 발생: " + e.getMessage());
        }
    }
}
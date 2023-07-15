package com.v3.furry_frined_chat_cud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.v3.furry_frined_chat_cud.common.dto.JwtResponse;
import com.v3.furry_frined_chat_cud.common.service.TokenService;
import com.v3.furry_frined_chat_cud.dto.ChatMessageResponseDTO;
import com.v3.furry_frined_chat_cud.dto.ChatParticipantsResponseDTO;
import com.v3.furry_frined_chat_cud.dto.ChatRoomMessageResponseDTO;
import com.v3.furry_frined_chat_cud.dto.ChatRoomRequestDTO;
import com.v3.furry_frined_chat_cud.dto.ChatRoomResponseDTO;
import com.v3.furry_frined_chat_cud.entity.ChatMessage;
import com.v3.furry_frined_chat_cud.entity.ChatRoom;
import com.v3.furry_frined_chat_cud.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatParticipantsService chatParticipantsService;
    private final TokenService tokenService;

    // 한 유저의 모든 채팅방 조회
    public List<ChatRoomMessageResponseDTO> findMemberChatRoom(String accessToken) throws Exception{

        try{
            JwtResponse jwtResponse = tokenService.getMember(accessToken);

            // Pageable pageable = PageRequest.of(0, 1);
            List<Object []> result = chatRoomRepository.findChatRoomByMember(jwtResponse.getMemberId());

            ChatRoom chatRoom = new ChatRoom();
            ChatMessage chatMessage = new ChatMessage();
            List<ChatRoomMessageResponseDTO> chatRoomMessageResponseDTOList = new ArrayList<>();

            result.forEach(arr -> {

                ChatRoomMessageResponseDTO chatRoomMessageResponseDTO = ChatRoomMessageResponseDTO.builder()
                    .chatMessageResponseDTO(chatMessage.entityToDTO((ChatMessage) arr[1]))
                    .chatRoomResponseDTO(chatRoom.entityToDTO((ChatRoom) arr[0]))
                    .build();
                chatRoomMessageResponseDTOList.add(chatRoomMessageResponseDTO);
            });

            return chatRoomMessageResponseDTOList;
        }catch (Exception e){
            log.error("ChatRoomService 한 유저의 모든 채팅방 조회 실패: " + e.getMessage(), e);
            throw new Exception("ChatRoomService 한 유저의 모든 채팅방 조회 실패: " + e.getMessage());
        }
    }

    // 채팅방 조회
    public ChatRoom findChatRoom(Long chatRoomId) throws Exception{
        try{
            return chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new Exception("ChatRoomService 채팅방 조회 실패: "));
        }catch (Exception e){
            log.error("ChatRoomService 채팅방 조회 실패: " + e.getMessage(), e);
            throw new Exception("ChatRoomService 채팅방 조회 실패: " + e.getMessage());
        }
    }

    // 채팅방 생성
    public ChatParticipantsResponseDTO createChatRoom(ChatRoomRequestDTO chatRoomRequestDTO) throws Exception{

        try{

            JwtResponse jwtResponse = tokenService.getMember(chatRoomRequestDTO.getJwtRequest().getAccess_token());
            if (jwtResponse.getMemberId() == null || jwtResponse.getMemberName() == null){
                throw new Exception("토큰 오류가 발생했습니다.");
            }

            ChatRoom chatRoom = ChatRoom.builder()
                .chatCreator(jwtResponse.getMemberId())
                .chatDel(false)
                .chatName("{" + jwtResponse.getMemberName() + "} 님이 만드신 채팅방")
                .build();

            // chatRoom을 저장할 때 참여자 추가 저장 및 리턴
            return chatParticipantsService.createChatParticipants(chatRoom, chatRoomRequestDTO);
        }catch (Exception e){
            log.info("ChatRoomService 채팅방 생성 에러 발생: " + e.getMessage(), e);
            throw new Exception("ChatRoomService 채팅방 생성 에러 발생: " + e.getMessage());
        }
    }

    // 채팅방 나가기
    public void deleteChatRoom(String accessToken, Long chatRoomId) {

        try {
            JwtResponse jwtResponse = tokenService.getMember(accessToken);
            ChatRoom chatRoom = chatRoomRepository.findChatRoomByChatRoomId(chatRoomId);
            
            Long memberId = jwtResponse.getMemberId();

            // 채팅방 참여자 중 생성자가 방을 나갔을 때
            if (chatRoom.getChatCreator().equals(memberId)){
                chatRoom.setChatDel(true);
                chatRoomRepository.save(chatRoom);
            // 채팅방 참여자가 1명.
            // 채팅방 참여자 중 참여자가 방을 나갔을 때
            } else if (chatRoom.getChatParticipants().get(0).getChatParticipantsId().equals(memberId)) {
                chatParticipantsService.deleteChatParticipants(chatRoomId, jwtResponse);
            } else{
                throw new Exception("채팅방 참여자가 아닙니다.");
            }
        }catch (Exception e){
            log.info("ChatRoomService 에러 발생: " + e.getMessage(), e);
        }

    }
}

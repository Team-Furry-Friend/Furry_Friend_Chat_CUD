package com.v3.furry_frined_chat_cud.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.v3.furry_frined_chat_cud.dto.ChatMessageRequest1;
import com.v3.furry_frined_chat_cud.dto.ChatMessageResponse;
import com.v3.furry_frined_chat_cud.service.ChatMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ChatController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chats")
    @SendTo("/chats")
    public ChatMessageResponse chatting(ChatMessageRequest1 chatMessageRequest) {

        log.info("{request : {}}", chatMessageRequest.getContent());

        return null;
    }

    @MessageMapping("/{chat_room_id}")
    @SendTo("/sub/{chat_room_id}")
     public ChatMessageResponse chatting(ChatMessageRequest1 chatMessageRequest, @DestinationVariable(value = "chat_room_id") Long chat_room_id) {

        log.info("{roomNo : {}, request : {}}", chat_room_id, chatMessageRequest);

         return null;
     }

}

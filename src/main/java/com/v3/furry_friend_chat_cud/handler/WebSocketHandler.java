package com.v3.furry_friend_chat_cud.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 메시지를 받았을 때 처리
        String payload = message.getPayload();
        // 메시지 타입이 disconnect인 경우 세션 종료
        if ("disconnect".equals(payload)) {
            session.close();
        }
    }
}
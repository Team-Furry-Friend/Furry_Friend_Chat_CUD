package com.v3.furry_frined_chat_cud.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.v3.furry_frined_chat_cud.common.response.ApiResponse;
import com.v3.furry_frined_chat_cud.dto.Greeting;
import com.v3.furry_frined_chat_cud.dto.HelloMessage;

@Controller
public class ChatController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
     public Greeting greeting(HelloMessage message) throws Exception {
         Thread.sleep(1000); // simulated delay
         return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
     }

}

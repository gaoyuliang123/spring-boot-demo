package com.example.websocket.controller;

import com.example.websocket.model.InMessage;
import com.example.websocket.service.WebScoketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChatRoomControllerV3 {

    @Autowired
    private WebScoketService webScoketService;

    // http://localhost:8080/v3/index.html
    @MessageMapping("/v3/single/chat")
    public String chatRoom(InMessage message) {
        webScoketService.sendChatMessage("/chat/single/", message);
        return "success";
    }
}

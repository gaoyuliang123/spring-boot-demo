package com.example.websocket.controller;

import com.example.websocket.model.InMessage;
import com.example.websocket.model.OutMessage;
import com.example.websocket.service.WebScoketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameInfoControllerV2 {
    @Autowired
    private WebScoketService webScoketService;

    // http://localhost:8080/v2/index.html
    @MessageMapping("/v2/chat")
    public String gameInfo(InMessage message) {
        webScoketService.sendTopicMessage("/topic/game_rank", message);
        return "success";
    }
}

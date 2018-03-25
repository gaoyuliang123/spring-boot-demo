package com.example.websocket.controller;

import com.example.websocket.model.InMessage;
import com.example.websocket.model.OutMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameInfoControllerV1 {

    // http://localhost:8080/v1/index.html
    // http://localhost:8080/v1/admin.html 类似于管理员发布消息

    // @MessageMapping是用于客户端发送数据到服务端的路由配置
    @MessageMapping("/v1/chat")
    @SendTo("/topic/game_chat")
    public OutMessage gameInfo(InMessage message) {
        return new OutMessage(message.getContent());
    }
}

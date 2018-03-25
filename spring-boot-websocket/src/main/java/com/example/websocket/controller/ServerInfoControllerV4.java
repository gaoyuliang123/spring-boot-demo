package com.example.websocket.controller;

import com.example.websocket.model.InMessage;
import com.example.websocket.service.WebScoketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
@EnableScheduling
public class ServerInfoControllerV4 {

    @Autowired
    private WebScoketService webScoketService;

    /**
     * 实时推送服务器的JVM负载，已用内存等消息
     * // http://localhost:8080/v4/index.html
     * @return
     */
    // @MessageMapping("/v4/schedule/push")
    // 方法不能加参数
    @Scheduled(fixedRate = 3000 )
    public String serverInfo() {
        webScoketService.sendServerInfo();
        return "success";
    }
}

package com.example.websocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * 订阅事件监听
 */
@Component
public class SubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {
    @Override
    public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
        System.out.println("SubscribeEventListener->onApplicationEvent-->+" + stompHeaderAccessor.getCommand().getMessageType());
    }
}

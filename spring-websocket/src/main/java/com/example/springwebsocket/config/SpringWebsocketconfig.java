package com.example.springwebsocket.config;

import com.example.springwebsocket.handler.SpringWebSocketHandler;
import com.example.springwebsocket.interceptor.SpringWebscoketHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @description:
 * @author: TGL
 * @date: 2018/6/16 20:01
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class SpringWebsocketconfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{
    @Autowired
    private SpringWebscoketHandlerInterceptor springWebscoketHandlerInterceptor;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/websocket/socketServer.do")
                .addInterceptors(springWebscoketHandlerInterceptor);
        registry.addHandler(webSocketHandler(), "/socketjs/socketServer.do")
                .addInterceptors(springWebscoketHandlerInterceptor).withSockJS();
    }

    @Bean
    public TextWebSocketHandler webSocketHandler() {
        return new SpringWebSocketHandler();
    }
}

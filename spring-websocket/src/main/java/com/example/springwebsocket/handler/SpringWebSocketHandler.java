package com.example.springwebsocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 该对象提供了客户端连接,关闭,错误,发送等方法,重写这几个方法即可实现自定义业务逻辑
 * @author: TGL
 * @date: 2018/6/16 20:12
 */
@Component
public class SpringWebSocketHandler extends TextWebSocketHandler {
    Logger log = LoggerFactory.getLogger(SpringWebSocketHandler.class);
    public static final ConcurrentHashMap<String, WebSocketSession> users = new ConcurrentHashMap<>(16);

    /**
     * 连接成功
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("connect to the websocket success...... users={}", users.size());
        String userId = session.getId();
        users.putIfAbsent(userId, session);
        //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
        //TextMessage returnMessage = new TextMessage("你将收到的离线");
        //session.sendMessage(returnMessage);
    }

    /**
     * 消息处理:js调用websocket.send时候，会调用该方法
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    /**
     * 处理消息异常
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        log.info("websocket connection closed......");
        String userId = session.getId();
        users.remove(userId);

    }

    /**
     * 连接关闭
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("websocket connection closed......");
        String userName = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        log.info("用户"+ userName + "已退出！");
        String userId = session.getId();
        users.remove(userId);
        log.info("剩余在线用户"+users.size());
    }

    /**
     * 给某个用户发送消息
     * @param userId
     * @param message
     */
    public void sendMessageToUser(String userId, TextMessage message) {
        users.forEach((id, session) -> {
            if (id == userId) {
                if (session.isOpen()) {
                    try {
                        session.sendMessage(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return;
            }
        });
    }

    /**
     * 给所有的用户发送消费
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        users.forEach((id, session) -> {
                if (session.isOpen()) {
                    try {
                        session.sendMessage(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        });
    }
}

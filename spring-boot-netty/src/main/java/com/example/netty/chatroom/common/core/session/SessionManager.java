package com.example.netty.chatroom.common.core.session;

import com.example.netty.chatroom.common.core.model.Response;
import com.example.netty.chatroom.common.core.serializer.Serializer;
import com.google.protobuf.GeneratedMessage;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 会话对象管理类
 * @author: TGL
 * @date: 2018/5/27 15:45
 */
public class SessionManager {
    /**
     * 在线会话
     */
    private static final ConcurrentHashMap<Long, Session> onlineSessions = new ConcurrentHashMap<>();

    /**
     * 加入会话
     */
    public static boolean putSession(long playerId, Session session) {
        if (!onlineSessions.containsKey(playerId)) {
            return onlineSessions.putIfAbsent(playerId, session) == null ? true : false;
        }
        return false;
    }

    /**
     * 移除会话
     */
    public static Session removeSession(long playerId) {
        return onlineSessions.remove(playerId);
    }

    /**
     * 发送自定义协议消息
     */
    public static <T extends Serializer>  void sendMessage(Short module, Short cmd, long playerId, T message) {
        Session session = onlineSessions.get(playerId);
        if (session != null && session.isConnected()) {
            Response response = new Response(module, cmd, message.getBytes());
            session.write(response);
        }
    }

    /**
     * 发送消息[protoBuf协议]
     * @param <T>
     * @param playerId
     * @param message
     */
    public static <T extends GeneratedMessage> void sendMessage(long playerId, short module, short cmd, T message){
        Session session = onlineSessions.get(playerId);
        if (session != null && session.isConnected()) {
            Response response = new Response(module, cmd, message.toByteArray());
            session.write(response);
        }
    }

    /**
     * 玩家是否在线
     * @param playerId
     * @return
     */
    public static boolean isOnlinePlayer(long playerId) {
        return onlineSessions.containsKey(playerId);
    }

    /**
     * 获取所有在线玩家
     * @return
     */
    public static Set<Long> getOnlinePlayers() {
        return Collections.unmodifiableSet(onlineSessions.keySet());
    }
}

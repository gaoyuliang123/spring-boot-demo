package com.example.websocket.interceptor;

import com.example.websocket.controller.UsersChatControllerV6;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

/**
 * 频道拦截器 ，类似管道，可以获取消息的一些meta数据
 */
public class SocketChannelInterceptor extends ChannelInterceptorAdapter {

    /**
     * 在消息被实际发送到频道之前调用
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        return super.preSend(message, channel);
    }

    /**
     * 发送消息调用后立即调用
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        // 消息头
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(message);
        // 避免非stomp消息类型，例如心跳检测
        if (stompHeaderAccessor.getCommand() == null) {
            return;
        }
        String sessionId = stompHeaderAccessor.getSessionAttributes().get("sessionId").toString();
        System.out.println("SocketChannelIntecepter -> sessionId = " + sessionId);

        switch(stompHeaderAccessor.getCommand()) {
            case CONNECT:
                connect(sessionId);
                break;
            case DISCONNECT:
                disconnect(sessionId);
                break;
            case SUBSCRIBE:
                // do something
                break;
            case UNSUBSCRIBE:
                // do something
                break;
            case SEND:
                // do something
                break;
            // case ...:
                // do something
               // break;
            default:
                // do something
                break;
        }
    }

    /**
     * 在完成发送之后进行调用，不管是否有异常发生，一般用于资源清理
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        super.afterSendCompletion(message, channel, sent, ex);
    }

    @Override
    public boolean preReceive(MessageChannel channel) {
        return super.preReceive(channel);
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        return super.postReceive(message, channel);
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        super.afterReceiveCompletion(message, channel, ex);
    }

    //连接成功
    private void connect(String sessionId){
        System.out.println("connect sessionId=" + sessionId);
    }


    //断开连接
    private void disconnect(String sessionId){
        System.out.println("disconnect sessionId=" + sessionId);
        //用户下线操作
        UsersChatControllerV6.onlineUsers.remove(sessionId);
    }
}

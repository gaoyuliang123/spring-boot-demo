package com.example.netty.chatroom.common.core.session;

/**
 * @description: 会话抽象接口
 * @author: TGL
 * @date: 2018/5/27 15:35
 */
public interface Session {

    /**
     * 获取绑定的会话对象
     * @return
     */
    Object getAttachment();

    /**
     * 绑定对象
     */
    void setAttachment(Object attachment);

    /**
     * 移除绑定对象
     */
    void removeAttachment();

    /**
     * 向会话中写入消息
     */
    void write(Object message);

    /**
     * 判断会话是否连接中
     */
    boolean isConnected();

    /**
     * 会话关闭
     */
    void close();

}

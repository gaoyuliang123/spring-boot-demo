package com.example.netty.chatroom.server.module.service;

import com.example.netty.chatroom.common.core.session.Session;
import com.example.netty.chatroom.common.module.player.response.PlayerResponse;

/**
 * @description: 玩家服务
 * @author: TGL
 * @date: 2018/5/28 21:44
 */
public interface PlayService {
    /**
     * 注册并登陆
     * @param session
     * @param playerName
     * @param password
     * @return
     */
    public PlayerResponse registerAndLogin(Session session, String playerName, String password);

    /**
     * 登录
     * @param session
     * @param playerName
     * @param password
     * @return
     */
    public PlayerResponse login(Session session, String playerName, String password);
}

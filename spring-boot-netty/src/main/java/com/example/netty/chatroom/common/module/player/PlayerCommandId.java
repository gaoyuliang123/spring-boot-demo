package com.example.netty.chatroom.common.module.player;

/**
 * @description:
 * @author: TGL
 * @date: 2018/5/28 22:55
 */
public interface PlayerCommandId {

    /**
     * 创建并登录帐号
     */
    short REGISTER_AND_LOGIN = 1;

    /**
     * 登录帐号
     */
    short LOGIN = 2;
}

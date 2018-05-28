package com.example.netty.chatroom.common.module.player.request;

import com.example.netty.chatroom.common.core.serializer.Serializer;

/**
 * @description: 注册请求
 * @author: TGL
 * @date: 2018/5/28 22:44
 */
public class RegisterRequest extends Serializer{

    private String playerName;

    private String password;

    @Override
    protected void read() {
        playerName = super.readString();
        password = super.readString();
    }

    @Override
    protected void write() {
        super.writeString(playerName);
        super.writeString(password);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

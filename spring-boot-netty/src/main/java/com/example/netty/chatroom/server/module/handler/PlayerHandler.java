package com.example.netty.chatroom.server.module.handler;

import com.example.netty.chatroom.common.core.annotation.SocketCommand;
import com.example.netty.chatroom.common.core.annotation.SocketModule;
import com.example.netty.chatroom.common.core.model.Result;
import com.example.netty.chatroom.common.core.session.Session;
import com.example.netty.chatroom.common.module.ModuleId;
import com.example.netty.chatroom.common.module.player.PlayerCommandId;
import com.example.netty.chatroom.common.module.player.response.PlayerResponse;

/**
 * @description:
 * @author: TGL
 * @date: 2018/5/28 22:48
 */
@SocketModule(module = ModuleId.PLAYER)
public interface PlayerHandler {

    @SocketCommand(cmd = PlayerCommandId.REGISTER_AND_LOGIN)
    public Result<PlayerResponse> registerAndLogin(Session session, byte[] data);

    @SocketCommand(cmd = PlayerCommandId.LOGIN)
    public Result<PlayerResponse> login(Session session, byte[] data);
}

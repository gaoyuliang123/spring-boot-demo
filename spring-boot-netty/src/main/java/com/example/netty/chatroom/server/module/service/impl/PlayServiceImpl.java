package com.example.netty.chatroom.server.module.service.impl;

import com.example.netty.chatroom.common.core.model.ResultCode;
import com.example.netty.chatroom.common.core.session.Session;
import com.example.netty.chatroom.common.core.session.SessionManager;
import com.example.netty.chatroom.common.exception.ErrorCodeException;
import com.example.netty.chatroom.common.module.player.response.PlayerResponse;
import com.example.netty.chatroom.server.module.dao.PlayerDao;
import com.example.netty.chatroom.server.module.entity.Player;
import com.example.netty.chatroom.server.module.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: TGL
 * @date: 2018/5/28 21:44
 */
@Service
public class PlayServiceImpl implements PlayService {

    @Autowired
    private PlayerDao playerDao;

    @Override
    public PlayerResponse registerAndLogin(Session session, String playerName, String password) {
        Player extPlayer = playerDao.getPlayerByName(playerName);
        if (extPlayer != null) {
            throw new ErrorCodeException(ResultCode.PLAYER_EXIST);
        }
        Player player = new Player();
        player.setPlayerName(playerName);
        player.setPassward(password);
        playerDao.createPlayer(player);
        return this.login(session, playerName, password);
    }

    @Override
    public PlayerResponse login(Session session, String playerName, String password) {
        // 判断当前会话是否已登录
        if (session.getAttachment() != null) {
            throw new ErrorCodeException(ResultCode.HAS_LOGIN);
        }

        // 玩家不存在
        Player player = playerDao.getPlayerByName(playerName);
        if (player == null) {
            throw new ErrorCodeException(ResultCode.PLAYER_NO_EXIST);
        }

        // 密码错误
        if (!player.getPassward().equals(password)) {
            throw new ErrorCodeException(ResultCode.PASSWARD_ERROR);
        }

        // 判断是否在其他地方登录过
        boolean isOnlinePlayer = SessionManager.isOnlinePlayer(player.getPlayerId());
        if (isOnlinePlayer) {
            Session oldSession = SessionManager.removeSession(player.getPlayerId());
            oldSession.removeAttachment();
            // 踢下线
            oldSession.close();
        }
        // 加入在线会话
        if (SessionManager.putSession(player.getPlayerId(), session)) {
            session.setAttachment(player);
        } else {
            throw new ErrorCodeException(ResultCode.LOGIN_FAIL);
        }

        // 创建Response传输对象返回
        PlayerResponse playerResponse = new PlayerResponse();
        playerResponse.setPlayerId(player.getPlayerId());
        playerResponse.setPlayerName(player.getPlayerName());
        playerResponse.setLevel(player.getLevel());
        playerResponse.setExp(player.getExp());
        return playerResponse;
    }
}

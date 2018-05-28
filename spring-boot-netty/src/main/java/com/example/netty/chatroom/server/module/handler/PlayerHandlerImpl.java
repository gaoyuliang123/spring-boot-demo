package com.example.netty.chatroom.server.module.handler;

import com.example.netty.chatroom.common.core.model.Result;
import com.example.netty.chatroom.common.core.model.ResultCode;
import com.example.netty.chatroom.common.core.session.Session;
import com.example.netty.chatroom.common.exception.ErrorCodeException;
import com.example.netty.chatroom.common.module.player.request.LoginRequest;
import com.example.netty.chatroom.common.module.player.request.RegisterRequest;
import com.example.netty.chatroom.common.module.player.response.PlayerResponse;
import com.example.netty.chatroom.server.module.service.PlayService;
import com.sun.javaws.exceptions.ErrorCodeResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @description: 玩家模块
 * @author: TGL
 * @date: 2018/5/28 22:48
 */
@Component
public class PlayerHandlerImpl implements PlayerHandler {
    @Autowired
    private PlayService playService;
    @Override
    public Result<PlayerResponse> registerAndLogin(Session session, byte[] data) {
        RegisterRequest resgisterRequest = new RegisterRequest();
        PlayerResponse result = null;
        try {
            //反序列化
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.readFromBytes(data);
            //参数判空
            if(StringUtils.isEmpty(registerRequest.getPlayerName()) || StringUtils.isEmpty(registerRequest.getPassword())){
                return Result.ERROR(ResultCode.PLAYERNAME_NULL);
            }
            result = playService.registerAndLogin(session, registerRequest.getPlayerName(),
                    registerRequest.getPassword());
        } catch (ErrorCodeException e) {
            return Result.ERROR(e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
        }
        return Result.SUCCESS(result);
    }

    @Override
    public Result<PlayerResponse> login(Session session, byte[] data) {
        PlayerResponse result = null;
        try {
            //反序列化
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.readFromBytes(data);

            //参数判空
            if(StringUtils.isEmpty(loginRequest.getPlayerName()) || StringUtils.isEmpty(loginRequest.getPassword())){
                return Result.ERROR(ResultCode.PLAYERNAME_NULL);
            }

            //执行业务
            result = playService.login(session, loginRequest.getPlayerName(), loginRequest.getPassword());
        } catch (ErrorCodeException e) {
            return Result.ERROR(e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.UNKOWN_EXCEPTION);
        }
        return Result.SUCCESS(result);
    }
}

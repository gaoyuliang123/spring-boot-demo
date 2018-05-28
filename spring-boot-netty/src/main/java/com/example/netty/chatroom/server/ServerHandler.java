package com.example.netty.chatroom.server;

import com.example.netty.chatroom.common.core.model.Request;
import com.example.netty.chatroom.common.core.model.Response;
import com.example.netty.chatroom.common.core.model.Result;
import com.example.netty.chatroom.common.core.model.ResultCode;
import com.example.netty.chatroom.common.core.serializer.Serializer;
import com.example.netty.chatroom.common.core.session.Session;
import com.example.netty.chatroom.common.core.session.impl.SessionImpl;
import com.example.netty.chatroom.common.module.ModuleId;
import com.example.netty.chatroom.server.invoker.Invoker;
import com.example.netty.chatroom.server.invoker.InvokerManager;
import com.example.netty.chatroom.server.module.entity.Player;
import com.google.protobuf.GeneratedMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description:
 * @author: TGL
 * @date: 2018/5/26 22:54
 */
public class ServerHandler extends SimpleChannelInboundHandler<Request> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Request request) throws Exception {
        System.out.println("serverHandler-channelRead0======>" + Thread.currentThread().getName());
        this.handlerMessage(new SessionImpl(channelHandlerContext.channel()), request);
    }

    private void handlerMessage(Session session, Request request) {
        System.out.println("serverHandler-channelRead0======>" + Thread.currentThread().getName());
        short module = request.getModule();
        short cmd = request.getCmd();
        System.out.println("module:" + module + " cmd：" + cmd);
        Response response = new Response(request);
        // 获取命令执行器
        Invoker invoker = InvokerManager.getInvoker(request.getModule(), request.getCmd());
        if (invoker != null) {
            Result result = null;
            try {
                // 假如是玩家模块传入channel参数，否则传入playerId参数
                if (module == ModuleId.PLAYER) {
                    result = (Result)invoker.invoke(session, request.getData());
                } else {
                    Object object = session.getAttachment();
                    if (object != null) {
                        Player player = (Player)object;
                        long playerId = player.getPlayerId();
                        result = (Result)invoker.invoke(playerId, request.getData());
                    } else {
                        // 会话未登录拒绝请求
                        response.setStateCode(ResultCode.LOGIN_PLEASE);
                        session.write(response);
                        return;
                    }
                }

                if (result.getResultCode() == ResultCode.SUCCESS) {
                    // 回写数据
                    Object object = result.getContent();
                    if (object != null) {
                        if (object instanceof Serializer) {
                            response.setData(((Serializer)object).getBytes());
                        } else if(object instanceof GeneratedMessage){
                            GeneratedMessage content = (GeneratedMessage)object;
                            response.setData(content.toByteArray());
                        } else {
                            System.out.println(String.format("不可识别传输对象:%s", object));
                        }
                    }
                    session.write(response);
                } else {
                    //返回错误码
                    response.setStateCode(result.getResultCode());
                    session.write(response);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                // 系统未知异常
                response.setStateCode(ResultCode.UNKOWN_EXCEPTION);
                session.write(response);
            }
        } else {
            // 未找到执行者
            response.setStateCode(ResultCode.NO_INVOKER);
            session.write(response);
            return;
        }
    }
}

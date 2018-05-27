package com.example.netty.chatroom.common.core.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @description: 数据包编码器
 * @author: TGL
 * @date: 2018/5/26 23:08
 */
public class ResponseEncoder extends MessageToByteEncoder {
    /**
     * 数据包基本长度
     */
    private static int BASE_LENTH = 4 + 2 + 2 + 4;
    /**
     *
     * @param channelHandlerContext
     * @param o
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {

    }
}

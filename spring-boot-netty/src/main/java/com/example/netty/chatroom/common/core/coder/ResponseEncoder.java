package com.example.netty.chatroom.common.core.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @description: 数据包解码器
 *
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+——-----——
 * |  包头	|  模块号      |  命令号    |   长度   |   数据   |
 * +——----——+——-----——+——----——+——----——+——-----——+—-----——
 * 包头4字节
 * 模块号2字节
 * 命令号2字节
 * 长度4字节(数据部分占有字节数量)
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

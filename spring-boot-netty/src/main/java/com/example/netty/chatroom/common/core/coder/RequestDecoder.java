package com.example.netty.chatroom.common.core.coder;

import com.example.netty.chatroom.common.core.model.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * /**
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
 *
 */
public class RequestDecoder extends ByteToMessageDecoder {
    /**
     * 数据包基本长度
     */
    private static int BASE_LENTH = 4 + 2 + 2 + 4;
    
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        System.out.println("RequestDecoder======>" + Thread.currentThread().getName());
        while (true) {
            if (byteBuf.readableBytes() > BASE_LENTH) {
                // 第一个可读数据包的位置
                int beginIndex;
                while (true) {
                    // 包头游标开始点
                    beginIndex = byteBuf.readerIndex();
                    // 标记开始读游标位置点
                    byteBuf.markReaderIndex();
                    if (byteBuf.readInt() == ConstantValue.HEADER_FLAG) {
                        break;
                    }
                    // 未读到包头标识略过一个字节
                    byteBuf.resetReaderIndex();
                    byteBuf.readByte();
                    //不满足
                    if (byteBuf.readableBytes() < BASE_LENTH) {
                        return;
                    }
                }

                // 读取模块号
                Short module = byteBuf.readShort();
                // 读取命令号
                Short cmd = byteBuf.readShort();

                // 读取数据长度
                int length = byteBuf.readInt();
                if (length < 0) {
                    channelHandlerContext.close();
                }
                // 数据包还没到齐
                if (byteBuf.readableBytes() < length) {
                    byteBuf.readerIndex(beginIndex);
                    return;
                }

                // 读取数据部分
                byte[] data = new byte[length];
                byteBuf.readBytes(data);

                Request message = new Request(module, cmd, data);
                out.add(message);
            } else {
                break;
            }
        }
        //数据不完整，等待完整的数据包
        return;
    }
}

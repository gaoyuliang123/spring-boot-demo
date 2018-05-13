package com.example.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
// Sharable表示此对象在channel间共享
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String message = new String(bytes, "UTF-8");
        System.out.println("服务端收到的消息： " + message);

        //向客户端写数据
        String response = "Netty rocks!";
        ByteBuf buffer = Unpooled.copiedBuffer(response.getBytes());
        ctx.write(buffer);//写入缓冲数组

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // flush掉所有写回的数据
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                // 当flush完成后关闭channel
                .addListener(ChannelFutureListener.CLOSE);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 出现异常时关闭channel
        ctx.close();
    }
}

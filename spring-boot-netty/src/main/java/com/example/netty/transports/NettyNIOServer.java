package com.example.netty.transports;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * Netty IO
 */
public class NettyNIOServer {
    public void server(int port) throws Exception {
        final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n", CharsetUtil.UTF_8));
        // 事件循环组
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            // 用来引导服务器配置
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work);
            // 使用NIO异步模式  同步OioServerSocketChannel
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.localAddress(new InetSocketAddress(port));
            // 指定ChannelInitializer初始化handlers
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    // 添加一个“入站”handler到ChannelPipeline
                    socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            // 连接后，写消息到客户端，写完后便关闭连接
                            ctx.writeAndFlush(buf).addListener(ChannelFutureListener.CLOSE);
                        }

                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            super.channelRead(ctx, msg);
                        }

                        @Override
                        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                            super.channelReadComplete(ctx);
                        }

                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            super.exceptionCaught(ctx, cause);
                        }
                    });
                }
            });
            // 绑定服务器接受连接
            ChannelFuture f = bootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args){
        try {
            new NettyNIOServer().server(8090);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

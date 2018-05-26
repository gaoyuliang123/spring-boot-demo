package com.example.netty.chatroom.server;

import com.example.netty.chatroom.common.core.coder.RequestDecoder;
import com.example.netty.chatroom.common.core.coder.ResponseEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @description: server启动类
 * @author: TGL
 * @date: 2018/5/26 22:32
 */
@Component
public class Server {

    public void start(int port) {
        // 用来引导服务器配置
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 事件循环组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            // 设置循环线程组实例
            serverBootstrap.group(bossGroup, workGroup);
            // 设置channel工厂
            serverBootstrap.channel(NioServerSocketChannel.class);
            // 设置管道
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new RequestDecoder());
                    socketChannel.pipeline().addLast(new ResponseEncoder());
                    socketChannel.pipeline().addLast(new ServerHandler());
                }
            });
            // 设置参数
            // 链接缓冲池队列大小
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            // 绑定端口
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println("server start !!!");
            //future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

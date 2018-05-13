package com.example.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        // 通过nio方式来接收连接和处理连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    // 设置nio类型的channel
                    .channel(NioServerSocketChannel.class)
                    // 设置监听端口
                    .localAddress(port)
                    // 有连接到达时会创建一个channel
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            // pipeline管理channel中的Handler，在channel队列中添加一个handler来处理业务
                            channel.pipeline().addLast("myHandler", new EchoServerHandler());
                        }
                    });
            // 配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture f = b.bind(port).sync();
            System.out.println(EchoServer.class.getName() + "started and listen on: " + f.channel().localAddress());
            // 应用程序会一直等待，直到channel关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭EventLoopGroup，释放掉所有资源包括创建的线程
            bossGroup.shutdownGracefully().sync();
            workerGroup
                    .shutdownGracefully().sync();
        }

    }

    public static void main(String[] args){
        try {
            new EchoServer(8888).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

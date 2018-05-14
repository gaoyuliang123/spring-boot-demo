package com.example.netty.transports;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * 非阻塞NIO
 */
public class PlainNIOServer {

    public void server(int port) throws Exception {
        // open Selector that handles channels
        Selector selector = Selector.open();
        // open ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // get ServerSocket
        ServerSocket serverSocket = serverSocketChannel.socket();
        // bind server to port
        serverSocket.bind(new InetSocketAddress(port));
        // set to non-blocking
        serverSocketChannel.configureBlocking(false);
        // register ServerSocket to selector and specify that it is interested in new accepted clients
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("Hi!".getBytes(Charset.forName("UTF-8")));
        while (true) {
            int n = selector.select();
            if (n > 0) {
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();
                    try {
                        // Check if event was because new client ready to get accepted
                        if (key.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel)key.channel();
                            SocketChannel client = server.accept();
                            System.out.println("Accepted connection from " + client);
                            client.configureBlocking(false);
                            // Accept client and register it to selector
                            client.register(selector, SelectionKey.OP_WRITE,  msg.duplicate());
                        }
                        // Check if event was because socket is ready to write data
                        if (key.isWritable()) {
                            SocketChannel client = (SocketChannel) key.channel();
                            ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                            // write data to connected client
                            while (byteBuffer.hasRemaining()) {
                                if (client.write(byteBuffer) == 0) {
                                    break;
                                }
                            }
                            client.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();
                        key.channel().close();
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        try {
            new PlainNIOServer().server(8089);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

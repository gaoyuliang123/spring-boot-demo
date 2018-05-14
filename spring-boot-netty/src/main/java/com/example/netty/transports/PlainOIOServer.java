package com.example.netty.transports;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 阻塞式IO
 */
public class PlainOIOServer {

    public void server(int port) throws Exception {
        // bind server to port
       final ServerSocket serverSocket = new ServerSocket(port);
        try {
            while (true) {
                // accept connection
                final Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                // create new thread to handle connection
                new Thread() {
                    @Override
                    public void run() {
                        OutputStream out;
                        try {
                            out = clientSocket.getOutputStream();
                            // write message to connected client
                            out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
                            out.flush();
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            serverSocket.close();
        }
    }

    public static void main(String[] args){
        try {
            new PlainOIOServer().server(8088);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

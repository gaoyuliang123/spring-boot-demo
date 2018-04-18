package com.example.javaee.multithread.ch05;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author tgl
 * @data create in 22:17 2018/4/16
 */
public class BlockingQueueTest {
    /**
     * 现有的程序模拟产生了16个日志，打印这些日志本来需要16s，
     * 现增加4个线程去调用parseLog(String log)方法分头去打印这16个日志，只要需4s。
     * @param args
     */
    public static void main(String[] args){
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(16);
        BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>(16);
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String log = linkedBlockingQueue.take();
                            parseLog(log);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 16; i++) {
            try {
                linkedBlockingQueue.put("log" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void parseLog(String log) {
        System.out.println("log======>" + log + " " + (new Date()).getSeconds());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

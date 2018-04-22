package com.example.javaee.multithread.ch06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPoolTest {
    public static void main(String[] args){
        ExecutorService  fixedThreadExecutor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            final int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(no + " into");
                        Thread.sleep(1000L);
                        System.out.println(no + " end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            fixedThreadExecutor.execute(runnable);
        }
        fixedThreadExecutor.shutdown();
        System.out.println("main thread end");
    }
}

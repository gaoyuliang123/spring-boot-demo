package com.example.javaee.multithread.ch06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPoolTest {
    public static void main(String[] args){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("------正在工作------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 8, 2, TimeUnit.SECONDS);
    }

}

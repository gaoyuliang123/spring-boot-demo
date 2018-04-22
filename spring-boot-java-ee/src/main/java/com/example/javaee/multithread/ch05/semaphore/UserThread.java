package com.example.javaee.multithread.ch05.semaphore;

import java.util.concurrent.Semaphore;

public class UserThread implements Runnable {
    private Semaphore semaphore;
    private String name;

    public UserThread(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("user[" + name + "]连接上");
        try {
            Thread.sleep(100L);
            // 获取接下执行的许可
            semaphore.acquire();
            System.out.println("user[" + name + "]开始处理任务");
            Thread.sleep(1000L);
            // 释放许下一个线程访问
            semaphore.release();
            System.out.println("user[" + name + "]处理任务结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

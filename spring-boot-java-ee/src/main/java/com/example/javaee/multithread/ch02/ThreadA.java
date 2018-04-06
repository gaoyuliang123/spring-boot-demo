package com.example.javaee.multithread.ch02;

public class ThreadA extends Thread {
    @Override
    public void run() {
        try {
            // do something
            Thread.sleep(500L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("这是线程A");
    }
}

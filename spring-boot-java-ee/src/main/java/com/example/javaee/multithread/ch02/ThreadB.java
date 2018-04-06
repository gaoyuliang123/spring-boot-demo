package com.example.javaee.multithread.ch02;

public class ThreadB implements Runnable {
    @Override
    public void run() {
        try {
            // do something
            Thread.sleep(500L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("这是线程B");
        Thread curThread = Thread.currentThread();
        System.out.println("线程B#name======>" + curThread.getName());
        System.out.println("线程B#Id======>" + curThread.getId());
        System.out.println("线程B#优先级======>" + curThread.getPriority());
        System.out.println("线程B#状态======>" + curThread.getState());
        System.out.println("线程B#是否为守护线程======>" + curThread.isAlive());
        System.out.println("线程活动数目======>" + Thread.activeCount());
    }
}

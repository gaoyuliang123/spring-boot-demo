package com.example.javaee.multithread.ch02;

public class ThreadLocalClient extends Thread{
    private ThreadLocalMain main;

    public ThreadLocalClient(ThreadLocalMain main) {
        this.main = main;
    }

    public ThreadLocalClient() {
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("线程" + Thread.currentThread().getName() +
                    "======>main[" + main.getNextNum() + "]");
        }
        // 每个线程用完记得remove
        main.getThreadLocal().remove();
    }
}

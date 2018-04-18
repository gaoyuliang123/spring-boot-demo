package com.example.javaee.multithread.ch03;

/**
 * @author tgl
 * @data create in 8:31 2018/4/14
 */
public class ThreadA extends Thread {
    private Count count;

    public ThreadA(Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        count.add();
        count.substract();
    }
}

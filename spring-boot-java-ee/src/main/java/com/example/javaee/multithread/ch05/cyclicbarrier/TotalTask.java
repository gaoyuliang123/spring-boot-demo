package com.example.javaee.multithread.ch05.cyclicbarrier;

public class TotalTask extends Thread {

    @Override
    public void run() {
        System.out.println("All task end! thanks all!");
    }
}

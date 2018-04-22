package com.example.javaee.multithread.ch05.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class BranchTask implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private String name;

    public BranchTask(CyclicBarrier cyclicBarrier, String name) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println("Task[" + name + "] begin!");
            Thread.sleep(1000L);
            System.out.println("Task[" + name + "] end! waiting······");
            cyclicBarrier.await();
            System.out.println("All task end. Task[" + name + "] do next work······");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

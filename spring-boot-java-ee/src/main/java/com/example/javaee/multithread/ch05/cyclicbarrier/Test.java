package com.example.javaee.multithread.ch05.cyclicbarrier;

import com.example.javaee.multithread.ch05.semaphore.UserThread;

import java.util.concurrent.CyclicBarrier;

public class Test {
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new TotalTask());
        for (int i = 0; i < 3; i++) {
            new Thread(new BranchTask(cyclicBarrier, ("任务" + i))).start();
        }
        System.out.println("main thread end");
    }
}

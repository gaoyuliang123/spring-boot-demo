package com.example.javaee.multithread.ch05.priorityblockingqueue.Humen;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author tgl
 * @data create in 23:31 2018/4/17
 */
public class Test {
    public static void main(String[] args){
        PriorityBlockingQueue<Human> humanPriorityBlockingQueue = new PriorityBlockingQueue<>(200, new HumanComparator());
        Thread thread = new Thread(new ProducerRunnable(humanPriorityBlockingQueue));
        thread.start();
        try {
            thread.join();
            new Thread(new ConsumerRunnable(humanPriorityBlockingQueue)).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

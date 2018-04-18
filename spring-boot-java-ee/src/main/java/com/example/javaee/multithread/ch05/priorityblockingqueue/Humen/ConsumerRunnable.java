package com.example.javaee.multithread.ch05.priorityblockingqueue.Humen;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 办理业务类：钱多的优先办理
 * @author tgl
 * @data create in 23:25 2018/4/17
 */
public class ConsumerRunnable implements  Runnable {

    private PriorityBlockingQueue<Human> humanPriorityBlockingQueue;

    public ConsumerRunnable(PriorityBlockingQueue<Human> humanPriorityBlockingQueue) {
        this.humanPriorityBlockingQueue = humanPriorityBlockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Human human = humanPriorityBlockingQueue.take();
                if (human != null) {
                    System.out.println(human.getName() + "正在办理业务------>金额：" + human.getMoney());
                } else {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

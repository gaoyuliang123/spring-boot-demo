package com.example.javaee.multithread.ch05.priorityblockingqueue.Humen;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 排队类
 * @author tgl
 * @data create in 23:17 2018/4/17
 */
public class ProducerRunnable implements Runnable {
    private static final String name = "明刚红李刘吕赵黄王孙朱曾游丽吴昊周郑秦丘";
    private Random random = new Random();
    private PriorityBlockingQueue<Human> humanPriorityBlockingQueue;

    public ProducerRunnable(PriorityBlockingQueue<Human> humanPriorityBlockingQueue) {
        this.humanPriorityBlockingQueue = humanPriorityBlockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < name.length(); i++) {
            Human human = new Human(("小" + name.charAt(i)), random.nextInt(10000));
            humanPriorityBlockingQueue.put(human);
            System.out.println(("小" + name.charAt(i)) + "正在排队------>金额：" + human.getMoney());
        }
    }
}

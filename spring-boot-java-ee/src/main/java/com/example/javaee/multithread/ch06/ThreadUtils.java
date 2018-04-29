package com.example.javaee.multithread.ch06;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadUtils {
    private static ThreadUtils instance;
    private static byte[] lock = new byte[0];
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAX_POOL_SIZE = CPU_COUNT*2 + 1;
    private static final int KEEP_ALIVE = 1;
    /**
     * 线程池的对象
     */
    private ThreadPoolExecutor threadPoolExecutor;

    private ThreadUtils() {

    }

    public static ThreadUtils getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    return new ThreadUtils();
                }
            }
        }
        return instance;
    }

    public void execute(Runnable runnable) {
        if (threadPoolExecutor == null) {
            /**
             * corePoolSize:核心线程数
             * maximumPoolSize：线程池所容纳最大线程数(workQueue队列满了之后才开启)
             * keepAliveTime：非核心线程闲置时间超时时长
             * unit：keepAliveTime的单位
             * workQueue：等待队列，存储还未执行的任务
             * threadFactory：线程创建的工厂
             * handler：异常处理机制
             *
             */
            threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>());
        }
        // 把一个任务丢到了线程池中
        threadPoolExecutor.execute(runnable);
    }

    // 把任务移除等待队列
    public void cancle(Runnable runnable) {
        if (runnable != null) {
            threadPoolExecutor.getQueue().remove(runnable);
        }
    }
}

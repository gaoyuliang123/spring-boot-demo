package com.example.javaee.multithread.ch06;

import org.apache.ibatis.javassist.expr.Instanceof;

import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

public class ThreadUtils {
    private static ThreadUtils instance;
    private static byte[] lock = new byte[0];
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAX_POOL_SIZE = CPU_COUNT*2 + 1;
    private static final int KEEP_ALIVE = 1;
    private static final String THREAD_NAME = "my-thread-";
    /**
     * 线程池的对象
     */
    private static ThreadPoolExecutor threadPoolExecutor;

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
   static {
       threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
               new LinkedBlockingQueue<>(), new MyThreadFactory(1));
   }
    public void execute(Runnable runnable) {
        if (threadPoolExecutor == null) {
            throw new RuntimeException("threadPoolExecutor == null");
        }
        // 把一个任务丢到了线程池中
        threadPoolExecutor.execute(runnable);
    }

    public void execute(FutureTask futureTask) {
        if (threadPoolExecutor == null) {
            throw new RuntimeException("threadPoolExecutor == null");
        }
        // 把一个任务丢到了线程池中
        threadPoolExecutor.execute(futureTask);
    }

    // 把任务移除等待队列
    public void cancle(Object object) {
        if (object != null && (object instanceof  Runnable || object instanceof  FutureTask)) {
            threadPoolExecutor.getQueue().remove(object);
        }
    }

    private static class MyThreadFactory implements ThreadFactory {
        private int mPriority;
        private ThreadGroup mthreadGroup;
        private AtomicInteger mInteger = new AtomicInteger(1);

        public MyThreadFactory(int mPriority) {
            this.mPriority = mPriority;
            mthreadGroup = Thread.currentThread().getThreadGroup();
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(mthreadGroup, r, THREAD_NAME + mInteger, 0);
            thread.setPriority(mPriority);
            return thread;
        }
    }
}

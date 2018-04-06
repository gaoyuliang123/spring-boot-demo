package com.example.javaee.multithread.ch02;

public class ThreadLocalMain {
    // 通过匿名内部类覆盖ThreadLocal的initialValue()，指定初始值。
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    public ThreadLocal<Integer> getThreadLocal() {
        return seqNum;
    }

    public Integer getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void main(String[] args){
        ThreadLocalMain main = new ThreadLocalMain();
        ThreadLocalClient t1 = new ThreadLocalClient(main);
        ThreadLocalClient t2 = new ThreadLocalClient(main);
        ThreadLocalClient t3 = new ThreadLocalClient(main);
        t1.start();
        t2.start();
        t3.start();
    }
}

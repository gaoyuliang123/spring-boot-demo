package com.example.javaee.multithread.ch03;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tgl
 * @data create in 23:40 2018/4/14
 */
public class AtomicIntegerTest {
    public static void main(String[] args){
        AtomicInteger ai  = new AtomicInteger(0);
        // 主要方法：
        System.out.println("获取当前的值======>" + ai.get());
        System.out.println("获取当前的值并设置新的值======>" + ai.getAndSet(5));
        System.out.println("获取当前的值并自增======>" + ai.getAndIncrement());
        System.out.println("获取当前的值并自减======>" + ai.getAndDecrement());
        System.out.println("获取当前的值,加上预期的值======>" + ai.getAndAdd(10));
        System.out.println("先自增再获取当前的值======>" + ai.incrementAndGet());
        System.out.println("获取当前的值======>" + ai.get());
    }
}

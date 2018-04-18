package com.example.javaee.multithread.ch03.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tgl
 * @data create in 10:12 2018/4/14
 */
public class Count {
    final ReentrantLock reentrantLock = new ReentrantLock();
    public void get() {
//        final ReentrantLock reentrantLock = new ReentrantLock();
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + " get begin-------");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " get end-------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void put() {
//        final ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " put begin-------");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " put end-------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}

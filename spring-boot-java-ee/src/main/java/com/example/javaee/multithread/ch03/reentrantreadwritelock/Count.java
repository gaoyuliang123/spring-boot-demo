package com.example.javaee.multithread.ch03.reentrantreadwritelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author tgl
 * @data create in 10:12 2018/4/14
 */
public class Count {
    private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private Lock readLock = rw.readLock();
    private Lock writeLock = rw.writeLock();
    public void get() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " get begin-------");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " get end-------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void put() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " put begin-------");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " put end-------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }
}

package com.example.javaee.multithread.ch03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tgl
 * @data create in 23:48 2018/4/14
 */
public class Singleton {
    private static Singleton singleton;
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private Singleton() {

    }
    public static Singleton getInstance() {
        if (singleton == null) {
            reentrantLock.lock();
            if (singleton == null) {
                singleton = new Singleton();
            }
            reentrantLock.unlock();
        }
        return  singleton;
    }
}

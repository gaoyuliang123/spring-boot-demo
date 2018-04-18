package com.example.javaee.multithread.ch03.lock;

/**
 * @author tgl
 * @data create in 10:19 2018/4/14
 */
public class ReentrantLockDemo {

    public static void main(String[] args){
        final Count count = new Count();
        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    count.get();
                }
            }.start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    count.put();
                }
            }.start();
        }
    }
}

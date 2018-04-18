package com.example.javaee.multithread.ch03;

/**
 * 模拟单个用户干活
 * @author tgl
 * @data create in 8:26 2018/4/14
 */
public class Count {

    private int num = 0;
    private byte[] lock = new byte[1];

//    public synchronized void add() {
    public void add() {
        synchronized (lock) {
            try {
                Thread.sleep(50);
                num++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " add num======>" + num);
        }

    }

    public void substract() {
        synchronized (lock) {
            try {
                Thread.sleep(50);
                num--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " substract num======>" + num);
        }

    }

    public int getNum() {
        return num;
    }
}

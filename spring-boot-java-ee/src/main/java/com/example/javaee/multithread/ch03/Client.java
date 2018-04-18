package com.example.javaee.multithread.ch03;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * @author tgl
 * @data create in 8:37 2018/4/14
 */
public class Client {
    public static void main(String[] args){
        Count count = new Count();
        try {
            for (int i = 0; i < 100; i++) {
                ThreadA threadA = new ThreadA(count);
                threadA.start();
            }
            // 等所有人活干完
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            System.out.println("所有人活干完num最后的值======>" + count.getNum());
    }
}

package com.example.javaee.multithread.debug;

public class MultiThreadDebug {

    public static void main(String[] args){
        System.out.println("main thread start");
        int sum = 0;
        // 条件断点 Condition i==5
        for (int i = 1; i <= 10; i++) {
            System.out.println("i = " + i);
            // 回到"上一步" = Drop Frame
            sum = sum + i;
        }
        System.out.println("sum = " + sum);
        // 多线程调试 Thread
        new Thread("mythread-1") {
            @Override
            public void run() {
                System.out.println("this is mythread one");
            }
        }.start();

        new Thread("mythread-2") {
            @Override
            public void run() {
                System.out.println("this is mythread two");
            }
        }.start();
        System.out.println("main thread end");
    }
}

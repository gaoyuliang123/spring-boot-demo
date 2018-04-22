package com.example.javaee.multithread.ch05.semaphore;

import java.util.concurrent.Semaphore;

public class Test {

    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(3, true);
        for (int i = 0; i < 10; i++) {
            new Thread(new UserThread(semaphore, ("用户" + i))).start();
        }
        System.out.println("main thread over");
    }
}

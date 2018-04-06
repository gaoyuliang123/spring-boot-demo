package com.example.javaee.multithread.ch02;

import java.util.concurrent.Callable;

public class ThreadC implements Callable<String> {
    @Override
    public String call() throws Exception {
        try {
            // do something
            Thread.sleep(500L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("这是线程C");
        return "Thread C";
    }
}

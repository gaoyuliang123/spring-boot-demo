package com.example.javaee.multithread.ch02;

public class UncaughtExceptionHandlerThreadB implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Thread:" + t.getId());
        System.out.println("Exception:" + e.getMessage());
    }
}

package com.example.javaee.multithread.ch06;



public class ThreadUtilsTest {
    public static void main(String[] args){
        ThreadTask threadTask = new ThreadTask();
        ThreadUtils threadUtils = ThreadUtils.getInstance();
        for (int i = 0; i < 30; i++) {
            threadUtils.execute(threadTask);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ThreadTask implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " wroking·······");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

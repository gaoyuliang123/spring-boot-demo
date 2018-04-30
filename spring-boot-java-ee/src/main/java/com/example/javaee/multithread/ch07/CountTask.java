package com.example.javaee.multithread.ch07;

import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {
    private int start;
    private int end;
    private static int splitSize = 2;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if ((end - start) <= splitSize) {
            for (int i = start; i <= end; i++) {
                sum = sum + i;
            }
        } else {
            int middle = (start + end) / 2;
            CountTask conutTask1 = new CountTask(start, middle);
            CountTask conutTask2 = new CountTask(middle + 1, end);
            conutTask1.fork();
            conutTask2.fork();
            int result1 = conutTask1.join();
            int result2 = conutTask2.join();
            sum = result1 + result2;
        }
        return sum;
    }
}

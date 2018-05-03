package com.example.javaee.designPattern.future.jdkfuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureMain {
    public static void main(String[] args){
        RealData realData = new RealData("yaya");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<String> futureTask = new FutureTask<String>(realData);
//        Future<String> futureTask = executorService.submit(realData);
        executorService.submit(futureTask);
        System.out.println("请求完毕");
        try {
            // 这里依然可以做额外的数据操作，这里使用sleep代替其他业务逻辑的处理
            Thread.sleep(500);
            // 相当于data.getResult ()，取得call()方法的返回值
            // 如果此时call()方法没有执行完成，则依然会等待
            System.out.println("数据 = " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end");

    }
}

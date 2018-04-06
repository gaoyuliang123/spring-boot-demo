package com.example.javaee.multithread.ch02;

public class ThreadMain {
    public static void main(String[] args){
        // 创建简单线程的三种方式
        // 1、继承Thread类，覆盖run()方法。
//        ThreadA a = new ThreadA();
//        a.start();
        // 2、实现Runable接口，实现run()方法。
        ThreadB b = new ThreadB();
        Thread t = new Thread(b);
        t.setUncaughtExceptionHandler(new UncaughtExceptionHandlerThreadB());
        t.start();
        System.out.println("这是主线程");
        // 3、实现Callable<E> 接口，实现call() 方法。
//        ThreadC c = new ThreadC();
//        FutureTask<String> futureTask = new FutureTask<String>(c);
//        new Thread(futureTask).start();
//        System.out.println("------主线程开始------");
//        try {
//            // 主线程只有get()了，才会往下走。
//            String result = futureTask.get();
//            System.out.println("------子线程返回结果======>" + result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println("------主线程结束------");
    }
}

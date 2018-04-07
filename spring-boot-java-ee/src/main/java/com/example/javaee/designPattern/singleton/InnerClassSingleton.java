package com.example.javaee.designPattern.singleton;

/**
 *  内部类单例
 * @author tgl
 * @data create in 10:09 2018/4/7
 */
public class InnerClassSingleton {

    private static class InnerClass {
        private static InnerClassSingleton singleton = new InnerClassSingleton();
    }

    public InnerClassSingleton getInstance() {
        return InnerClass.singleton;
    }
}

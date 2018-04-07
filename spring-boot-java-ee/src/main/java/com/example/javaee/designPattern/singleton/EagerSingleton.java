package com.example.javaee.designPattern.singleton;

/**
 * 饿汉式单例
 * @author tgl
 * @data create in 9:50 2018/4/7
 */
public class EagerSingleton {
    private static EagerSingleton singleton = new EagerSingleton();

    private EagerSingleton() {

    }

    public static EagerSingleton getInstance() {
        return singleton;
    }
}

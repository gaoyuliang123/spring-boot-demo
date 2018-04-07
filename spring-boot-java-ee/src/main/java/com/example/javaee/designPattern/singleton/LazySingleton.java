package com.example.javaee.designPattern.singleton;

/**
 * 懒汉式单例
 * @author tgl
 * @data create in 9:50 2018/4/7
 */
public class LazySingleton {
    private static LazySingleton singleton = null;

    private LazySingleton() {

    }

    public static synchronized LazySingleton getInstance() {
        if (singleton == null) {
            return new LazySingleton();
        }
        return singleton;
    }
}

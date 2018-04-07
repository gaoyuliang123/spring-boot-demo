package com.example.javaee.designPattern.singleton;

/**
 * 双重检查加锁单例
 * 可以既实现线程安全，又能够使性能不受很大的影响。
 * @author tgl
 * @data create in 9:58 2018/4/7
 */
public class DoubleCheckLockSingleton {
    private static volatile DoubleCheckLockSingleton singleton = null;
    private byte[] lock = new byte[1];

    private DoubleCheckLockSingleton() {

    }

    public DoubleCheckLockSingleton getInstance() {
        if (singleton == null) {
            synchronized (lock) {
                if (singleton == null) {
                    return new DoubleCheckLockSingleton();
                }
            }
        }
        return singleton;
    }
}

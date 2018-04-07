package com.example.javaee.designPattern.singleton;

/**
 * 枚举单例
 * 特性：自由序列化，线程安全，保证单例。
 * @author tgl
 * @data create in 10:20 2018/4/7
 */
public enum Singleton {
    /**
     * 定义一个枚举的元素，它就代表了Singleton的一个实例。
     */
    UNIQUEINSTANCE;

    /**
     * 单例可以有自己的操作
     */
    public void singletonOperate() {
        //  功能代码
    }

}

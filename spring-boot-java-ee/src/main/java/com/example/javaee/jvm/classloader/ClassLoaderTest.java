package com.example.javaee.jvm.classloader;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // AppClassLoader
        System.out.println(classLoader);
        // ExtClassLoader负责加载 JDK安装路径\jre\lib\ex
        System.out.println(classLoader.getParent());
        // BootstrapClassLoader负责加载 JDK安装路径\jre\lib
        // 没有获取到 ExtClassLoader的父Loader，原因是 BootstrapLoader（引导类加载器）是用C语言实现的，找不到一个确定的返回父Loader的方式，于是就返回null。
        System.out.println(classLoader.getParent().getParent());
        System.out.println("===================================================");
        load();
    }

    /**
     *  类加载有三种方式
     */
    public static void  load() throws ClassNotFoundException {
        //
        ClassLoader classLoader = HelloWorld.class.getClassLoader();
        System.out.println(classLoader);
        // 使用ClassLoader.loadClass()来加载类，不会执行初始化块
        classLoader.loadClass("com.example.javaee.jvm.classloader.Test");
        System.out.println("------------------------------------");
        // 使用Class.forName()来加载类，默认会执行初始化块
        Class.forName("com.example.javaee.jvm.classloader.Test");
        System.out.println("------------------------------------");
        Class.forName("com.example.javaee.jvm.classloader.Test", false, classLoader);
    }
}

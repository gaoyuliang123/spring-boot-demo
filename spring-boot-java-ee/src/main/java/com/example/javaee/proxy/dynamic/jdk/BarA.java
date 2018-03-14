package com.example.javaee.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 柜台A
 */
public class BarA implements InvocationHandler{

    private Object object;

    public BarA(Object object) {
        this.object = object;
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 代理对象调用的方法
     * @param args 调用的方法中的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("销售开始！------柜台：" + this.getClass().getSimpleName());
        method.invoke(object, args);
        System.out.println("销售结束！");
        return null;
    }
}

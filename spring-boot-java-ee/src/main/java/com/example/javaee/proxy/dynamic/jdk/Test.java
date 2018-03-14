package com.example.javaee.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args){
        MouTaiWine mouTaiWine = new MouTaiWine();
        WuliangyeWine wuliangyeWine = new WuliangyeWine();
        InvocationHandler barA = new BarA(mouTaiWine);
        InvocationHandler barA2= new BarA(wuliangyeWine);
        // 代理类通过 Proxy.newInstance() 方法生成
        SellWine dynamicProxy = (SellWine)Proxy.newProxyInstance(mouTaiWine.getClass().getClassLoader(),
                mouTaiWine.getClass().getInterfaces(), barA);
        SellWine dynamicProxy2= (SellWine)Proxy.newProxyInstance(wuliangyeWine.getClass().getClassLoader(),
                wuliangyeWine.getClass().getInterfaces(), barA2);
        dynamicProxy.maijiu();
        dynamicProxy2.maijiu();
        // 动态生成的代理类名称是包名+$Proxy+id序号
        System.out.println("dynamicProxy:" + dynamicProxy.getClass().getName());
        System.out.println("dynamicProxy2:" + dynamicProxy2.getClass().getName());

        // 静态代理和动态代理的区别是在于要不要开发者自己定义Proxy类。
    }
}

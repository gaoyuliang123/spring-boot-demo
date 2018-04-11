package com.example.javaee.designPattern.proxy;

/**
 * @author tgl
 * @data create in 22:09 2018/4/10
 */
public class ProxyObject extends AbstractObject {
    private RealObject realObject;
    @Override
    void operation() {
        System.out.println("------操作之前-------");
        realObject =  new RealObject();
        realObject.operation();
        System.out.println("------操作之后-------");
    }
}

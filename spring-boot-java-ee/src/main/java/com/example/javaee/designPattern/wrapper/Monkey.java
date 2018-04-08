package com.example.javaee.designPattern.wrapper;

/**
 * 具体构件(ConcreteComponent)角色：定义一个将要接收附加责任的类。
 * @author tgl
 * @data create in 23:08 2018/4/8
 */
public class Monkey implements TheGreatestSage {
    @Override
    public void move() {
        System.out.println("------Monkey move------");
    }
}

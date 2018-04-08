package com.example.javaee.designPattern.adaper;

/**
 * 类适配器模式
 * @author tgl
 * @data create in 21:10 2018/4/7
 */
public class Adaper2 extends Adapee implements Target {

    @Override
    public void providetTwoHoleSocket() {
        System.out.println("------两孔插座------");
    }
}

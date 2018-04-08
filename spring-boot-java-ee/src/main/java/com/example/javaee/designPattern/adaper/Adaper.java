package com.example.javaee.designPattern.adaper;

/**
 * 对象适配器模式
 * @author tgl
 * @data create in 21:10 2018/4/7
 */
public class Adaper implements Target {

    private Adapee adapee;

    public Adaper(Adapee adapee) {
        this.adapee = adapee;
    }

    @Override
    public void provideThreeHoleSocket() {
        adapee.provideThreeHoleSocket();
    }

    @Override
    public void providetTwoHoleSocket() {
        System.out.println("------两孔插座------");
    }
}

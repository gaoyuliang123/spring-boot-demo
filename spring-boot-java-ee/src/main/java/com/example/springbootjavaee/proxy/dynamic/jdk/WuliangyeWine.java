package com.example.springbootjavaee.proxy.dynamic.jdk;

public class WuliangyeWine implements SellWine {
    @Override
    public void maijiu() {
        System.out.println("------我卖的是五粮液酒------");
    }
}

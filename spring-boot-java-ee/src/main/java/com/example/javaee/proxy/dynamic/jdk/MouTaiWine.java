package com.example.javaee.proxy.dynamic.jdk;

public class MouTaiWine implements SellWine {
    @Override
    public void maijiu() {
        System.out.println("------我卖的是茅台酒------");
    }
}

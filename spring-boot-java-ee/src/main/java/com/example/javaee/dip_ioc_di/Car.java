package com.example.javaee.dip_ioc_di;

/**
 * 汽车
 */
public class Car implements Driveable {
    @Override
    public void drive() {
        System.out.println("------Car drive------");
    }
}

package com.example.javaee.dip_ioc_di;

/**
 * 自行车
 */
public class Bike implements Driveable {
    @Override
    public void drive() {
        System.out.println("------Bike drive------");
    }
}

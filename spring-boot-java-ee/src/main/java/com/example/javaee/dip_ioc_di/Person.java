package com.example.javaee.dip_ioc_di;

/**
 * DIP(依赖倒置)原则：
 * 上层模块不应该依赖底层模块，它们都应该依赖于抽象。
 * 抽象不应该依赖于细节，细节应该依赖于抽象。
 *
 */
public class Person {
//    private Bike bike;
//    private Car car;

    private Driveable driveable;

    public Person() {
//        bike = new Bike();
//        car = new Car();
        // DIP
        driveable = new Car();
    }

    // Ioc
    /**
     * 控制反转的意思是反转了上层模块对于底层模块的依赖控制。
     * @param driveable
     *
     * 实现依赖注入(injection)有 3 种方式：
        1. 构造函数中注入
        2. setter 方式注入
        3. 接口注入
     */
    public Person(Driveable driveable) {
        this.driveable = driveable;
    }

    public void go() {
        System.out.println("出门了------");
//        bike.drive();
//        car.drive();
        driveable.drive();
    }

    // setter 方式注入
    public void setDriveable(Driveable driveable) {
        this.driveable = driveable;
    }
}

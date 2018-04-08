package com.example.javaee.designPattern.wrapper;

import ch.qos.logback.core.db.dialect.SybaseSqlAnywhereDialect;

/**
 * @author tgl
 * @data create in 23:17 2018/4/8
 */
public class Fish extends Change {

    public Fish(TheGreatestSage sage) {
        super(sage);
    }

    @Override
    public void move() {
        super.move();
        System.out.println("------Fish move------");
    }
}

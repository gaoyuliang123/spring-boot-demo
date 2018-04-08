package com.example.javaee.designPattern.wrapper;

/**
 * @author tgl
 * @data create in 23:17 2018/4/8
 */
public class Bird extends Change {

    public Bird(TheGreatestSage sage) {
        super(sage);
    }

    @Override
    public void move() {
        super.move();
        System.out.println("------Bird move------");
    }
}

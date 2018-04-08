package com.example.javaee.designPattern.wrapper;

/**
 * @author tgl
 * @data create in 23:20 2018/4/8
 */
public class Test {
    public static void main(String[] args){
        TheGreatestSage sage = new Monkey();
        TheGreatestSage greatestSage = new Fish(new Bird(sage));
        greatestSage.move();
    }
}

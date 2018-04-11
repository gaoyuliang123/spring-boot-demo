package com.example.javaee.designPattern.strategy;

/**
 * @author tgl
 * @data create in 23:07 2018/4/10
 */
public class Test {
    public static void main(String[] args) {
        MemberStrategy strategy = new IntermediateMemberStrategy();
        Price price = new Price(strategy);
        double bookSprice = price.quto(200);
        System.out.println("price=" + bookSprice);
    }
}

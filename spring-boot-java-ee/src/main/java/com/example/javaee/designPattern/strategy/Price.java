package com.example.javaee.designPattern.strategy;

/**
 * @author tgl
 * @data create in 23:04 2018/4/10
 */
public class Price {
    private MemberStrategy memberStrategy;

    public Price(MemberStrategy memberStrategy) {
        this.memberStrategy = memberStrategy;
    }

    double quto(double bookPrice) {
        return this.memberStrategy.calcPrice(bookPrice);
    }
}

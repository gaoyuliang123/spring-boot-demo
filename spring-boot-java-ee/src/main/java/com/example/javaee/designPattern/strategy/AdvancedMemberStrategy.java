package com.example.javaee.designPattern.strategy;

/**
 * @author tgl
 * @data create in 23:01 2018/4/10
 */
public class AdvancedMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double bookPrice) {
        System.out.println("中级会员打五折");
        return bookPrice * 0.5;
    }
}

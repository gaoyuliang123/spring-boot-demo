package com.example.javaee.designPattern.strategy;

/**
 * @author tgl
 * @data create in 23:01 2018/4/10
 */
public class PrimaryMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double bookPrice) {
        System.out.println("初级会员没有折扣");
        return bookPrice;
    }
}

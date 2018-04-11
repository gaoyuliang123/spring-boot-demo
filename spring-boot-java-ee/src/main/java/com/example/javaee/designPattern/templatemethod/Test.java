package com.example.javaee.designPattern.templatemethod;

/**
 * @author tgl
 * @data create in 23:06 2018/4/11
 */
public class Test {

    public static void main(String[] args){
        Account mk = new MoneyMarketAccount();
        double amount1 =  mk.calcaulateInterest();
        System.out.println("货币市场账号的利息数额为：" + amount1);

        Account cd = new CDAccount();
        double amount2 =  cd.calcaulateInterest();
        System.out.println("定期账号的利息数额为：" + amount2);
    }
}

package com.example.javaee.designPattern.templatemethod;

/**
 * @author tgl
 * @data create in 23:04 2018/4/11
 */
public class MoneyMarketAccount extends Account {
    @Override
    protected String doCalculateAccountType() {
        return "MoneyMarket";
    }

    @Override
    protected double doCalculateInterestRate() {
        return 0.045;
    }
}

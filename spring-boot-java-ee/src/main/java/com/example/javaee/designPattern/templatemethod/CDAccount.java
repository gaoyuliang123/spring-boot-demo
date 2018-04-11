package com.example.javaee.designPattern.templatemethod;

/**
 * @author tgl
 * @data create in 23:05 2018/4/11
 */
public class CDAccount extends Account {
    @Override
    protected String doCalculateAccountType() {
        return "Certificate of Deposite";
    }

    @Override
    protected double doCalculateInterestRate() {
        return 0.03;
    }
}

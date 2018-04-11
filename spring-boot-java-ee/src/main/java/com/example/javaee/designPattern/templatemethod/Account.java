package com.example.javaee.designPattern.templatemethod;

/**
 * @author tgl
 * @data create in 22:52 2018/4/11
 */
public abstract class Account {

    /**
     * 模板方法，计算利息数额
     * @return    返回利息数额
     */
    public final double calcaulateInterest() {
        String accountType = this.doCalculateAccountType();
        double rate = this.doCalculateInterestRate();
        double amount = this.calculateAmount(accountType);
        return amount*rate;
    }

    /**
     * 基本方法留给子类实现
     */
    protected abstract String doCalculateAccountType();

    /**
     * 基本方法留给子类实现
     */
    protected abstract double doCalculateInterestRate();

    /**
     * 基本方法，已经实现
     */
    private double calculateAmount(String accountType) {
        /**
         * 省略相关的业务逻辑
         */
        return 7243.00;
    }
}

package com.example.javaee.multithread.ch05.priorityblockingqueue.Humen;

/**
 * 银行客户类
 * @author tgl
 * @data create in 23:12 2018/4/17
 */
public class Human {
    private String name;
    private int money;

    public Human() {
    }

    public Human(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}

package com.example.javaee.reflect;

import java.util.Date;

public class Son extends Father {
    int age;
    private Date birthday;
    protected float money;
    public String name;

    public Son() {
    }

    public Son(String name, String interest, int age, Date birthday, float money) {
        super(name, interest);
        this.age = age;
        this.birthday = birthday;
        this.money = money;
    }

    public Son(String name) {
        this.name = name;
    }

    protected Son(float money) {
        this.money = money;
    }

    private Son(Date birthday){
        this.birthday = birthday;
    }

    public void method1(String name) {
        System.out.println("调用了：公有的method1方法其String参数: name = " + name);
    }
    protected void method2() {
        System.out.println("调用了：受保护的method2方法无参");
    }
    void method3() {
        System.out.println("调用了：默认的method3方法无参");
    }
    private int method4(int age) {
        System.out.println("调用了：私有的method4方法其int参数: int = " + age + ",有返回值");
        return age;
    }

    @Override
    public String toString() {
        return "Son{" +
                "age=" + age +
                ", birthday=" + birthday +
                ", money=" + money +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args){
        System.out.println("------执行main方法------args:" + args.toString());
    }
}

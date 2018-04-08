package com.example.javaee.designPattern.wrapper;

/**
 * 装饰(Decorator)角色：
 * 持有一个构件(Component)对象的实例，并定义一个与抽象构件接口一致的接口。
 * @author tgl
 * @data create in 23:09 2018/4/8
 */
public class Change implements TheGreatestSage {
    private TheGreatestSage sage;

    public Change(TheGreatestSage sage) {
        this.sage = sage;
    }

    @Override
    public void move() {
        sage.move();
    }
}

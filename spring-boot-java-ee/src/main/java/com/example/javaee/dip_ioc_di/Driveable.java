package com.example.javaee.dip_ioc_di;

/**
 * 抽象可以是物也可以是行为：
 * 交通工具是抽象，而公交车、单车、火车等就是具体了。
 * 表演是抽象，而唱歌、跳舞、小品等就是具体。
 *
 * 具体映射到软件开发中，抽象可以是接口或者抽象类形式。
 */
public interface Driveable {
    void drive();
}

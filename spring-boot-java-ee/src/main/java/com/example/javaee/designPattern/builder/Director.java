package com.example.javaee.designPattern.builder;

/**
 * Director（导演者）
 * 调用具体建造者来创建复杂对象的各个部分，在指导者中不涉及具体产品的信息，只负责保证对象各部分完整创建或按某种顺序创建。
 * @author tgl
 * @data create in 16:28 2018/4/7
 */
public class Director {
    public Person constructPerson(Builder builder) {
        builder.builderHeat();
        builder.builderBody();
        builder.builderFoot();
        return builder.builderPerson();
    }
}

package com.example.javaee.designPattern.builder;

/**
 * ConcreteBuilder（具体建造者）
 * 实现Builder接口，针对不同的商业逻辑，具体化复杂对象的各部分的创建。 在建造过程完成后，提供产品的实例。
 * @author tgl
 * @data create in 16:24 2018/4/7
 */
public class ConcreteBuilder implements Builder {
    private Person person;

    public ConcreteBuilder() {
        this.person = new Person();
    }

    @Override
    public void builderHeat() {
        this.person.setHeat("建造头部");
    }

    @Override
    public void builderBody() {
        this.person.setBody("建造身体");
    }

    @Override
    public void builderFoot() {
        this.person.setFoot("建造脚部");
    }

    @Override
    public Person builderPerson() {
        return this.person;
    }
}

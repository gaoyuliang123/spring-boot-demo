package com.example.javaee.designPattern.factory.polymorphicfactory;

/**
 * 工厂方法模式是类的创建模式，又叫做虚拟构造子(Virtual Constructor)模式或者多态性工厂（Polymorphic Factory）模式。
 * 工厂方法模式的用意是定义一个创建产品对象的工厂接口，将实际创建工作推迟到子类中。
 *
 * @author tgl
 * @data create in 23:35 2018/4/6
 */
public interface ExportFactory {
    ExportFile factory(String type);
}

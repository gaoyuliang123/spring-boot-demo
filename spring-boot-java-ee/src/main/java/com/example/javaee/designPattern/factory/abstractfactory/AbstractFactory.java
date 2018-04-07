package com.example.javaee.designPattern.factory.abstractfactory;

/**
 * 抽象工厂模式与工厂方法模式的最大区别就在于，工厂方法模式针对的是一个产品等级结构；
 * 而抽象工厂模式则需要面对多个产品等级结构。
 *
 * 产品族，是指位于不同产品等级结构中，功能相关联的产品组成的家族。比如AMD的主板、芯片组、CPU组成一个家族，Intel的主板、芯片组、CPU组成一个家族。
 * 而这两个家族都来自于三个产品等级：主板、芯片组、CPU。一个等级结构是由相同的结构的产品组成。
 * @author tgl
 * @data create in 9:25 2018/4/7
 */
public interface AbstractFactory {
    CPU createCPU();
    MainBoard createMainBoard();
}

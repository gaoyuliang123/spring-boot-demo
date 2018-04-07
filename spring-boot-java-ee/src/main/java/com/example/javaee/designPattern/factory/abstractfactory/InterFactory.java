package com.example.javaee.designPattern.factory.abstractfactory;

/**
 * @author tgl
 * @data create in 9:26 2018/4/7
 */
public class InterFactory implements AbstractFactory {
    @Override
    public CPU createCPU() {
        return new InterCPU(755);
    }

    @Override
    public MainBoard createMainBoard() {
        return new InterMainBoard(755);
    }
}

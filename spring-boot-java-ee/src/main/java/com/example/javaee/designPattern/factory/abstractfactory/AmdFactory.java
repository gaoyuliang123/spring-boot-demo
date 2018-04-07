package com.example.javaee.designPattern.factory.abstractfactory;

/**
 * @author tgl
 * @data create in 9:26 2018/4/7
 */
public class AmdFactory implements AbstractFactory {
    @Override
    public CPU createCPU() {
        return new AmdCPU(983);
    }

    @Override
    public MainBoard createMainBoard() {
        return new AmdMainBoard(983);
    }
}

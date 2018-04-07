package com.example.javaee.designPattern.factory.abstractfactory;

/**
 * @author tgl
 * @data create in 8:42 2018/4/7
 */
public class AmdCPU implements CPU {
    /**
     * CPU 针脚数
     */
    private int pins = 0;

    public AmdCPU() {
    }

    public AmdCPU(int pins) {
        this.pins = pins;
    }

    @Override
    public void calculate() {
        System.out.println("------AMD CPU 针数====>" + pins);
    }
}

package com.example.javaee.designPattern.factory.abstractfactory;

/**
 * @author tgl
 * @data create in 8:42 2018/4/7
 */
public class InterMainBoard implements MainBoard {
    /**
     * CPU 插槽的孔数
     */
    private int cpuHoles = 0;

    public InterMainBoard() {
    }

    public InterMainBoard(int pins) {
        this.cpuHoles = pins;
    }


    @Override
    public void installCPU() {
        System.out.println("------Inter主板的CPU插槽的孔数====>" + cpuHoles);
    }
}

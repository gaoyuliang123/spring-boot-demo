package com.example.javaee.designPattern.factory.abstractfactory;

/**
 * @author tgl
 * @data create in 8:51 2018/4/7
 */
public class CPUFactory {
    public static CPU createCPU(int type) {
        if (1 == type) {
            return new InterCPU(755);
        } else if (2 == type) {
            return new AmdCPU(983);
        } else {
            throw new RuntimeException(" 没有找到合适的CPU");
        }
    }
}

package com.example.javaee.designPattern.factory.abstractfactory;

/**
 * @author tgl
 * @data create in 8:51 2018/4/7
 */
public class MainBoardFactory {
    public static MainBoard createMainBoard(int type) {
        if (1 == type) {
            return new InterMainBoard(755);
        } else if (2 == type) {
            return new AmdMainBoard(983);
        } else {
            throw new RuntimeException(" 没有找到合适的主板");
        }
    }
}

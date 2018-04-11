package com.example.javaee.designPattern.proxy;

import ch.qos.logback.core.db.dialect.SybaseSqlAnywhereDialect;

/**
 * @author tgl
 * @data create in 22:08 2018/4/10
 */
public class RealObject extends AbstractObject {
    @Override
    void operation() {
            System.out.println("------业务操作------");
    }
}

package com.example.javaee.designPattern.factory.polymorphicfactory;

/**
 * @author tgl
 * @data create in 23:33 2018/4/6
 */
public class ExportStandardPDFFile implements ExportFile {
    @Override
    public boolean export(String data) {
        //  业务逻辑
        System.out.println("------导出标准版PDF文件------");
        return true;
    }
}

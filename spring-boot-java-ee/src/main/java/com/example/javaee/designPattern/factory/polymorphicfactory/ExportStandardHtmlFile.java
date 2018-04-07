package com.example.javaee.designPattern.factory.polymorphicfactory;

/**
 * @author tgl
 * @data create in 23:31 2018/4/6
 */
public class ExportStandardHtmlFile implements ExportFile {
    @Override
    public boolean export(String data) {
        //  业务逻辑
        System.out.println("------导出标准版Html文件------");
        return true;
    }
}

package com.example.javaee.designPattern.factory.polymorphicfactory;

/**
 * @author tgl
 * @data create in 23:29 2018/4/6
 */
public class ExportFinancialHtmlFile implements ExportFile {
    @Override
    public boolean export(String data) {
        //  业务逻辑
        System.out.println("------导出财务版Html文件------");
        return true;
    }
}

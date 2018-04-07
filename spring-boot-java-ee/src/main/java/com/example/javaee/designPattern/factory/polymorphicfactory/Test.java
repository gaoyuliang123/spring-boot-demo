package com.example.javaee.designPattern.factory.polymorphicfactory;

/**
 * @author tgl
 * @data create in 23:44 2018/4/6
 */
public class Test {
    public static void main(String[] args){
        ExportFactory exportHtmlFactory = new ExportHtmlFactory();
        ExportFile exportHtmlFile = exportHtmlFactory.factory("STANDARD");
        exportHtmlFile.export("data");

        ExportFactory exportPDFFactory = new ExportPDFFactory();
        ExportFile exportPDFFile = exportPDFFactory.factory("ERROR");
        exportPDFFile.export("data");
    }
}

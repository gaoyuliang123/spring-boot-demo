package com.example.javaee.designPattern.factory.polymorphicfactory;

/**
 * @author tgl
 * @data create in 23:37 2018/4/6
 */
public class ExportHtmlFactory implements ExportFactory {
    private static final String STANDARD = "STANDARD";
    private static final String FINANCIAL = "FINANCIAL";
    @Override
    public ExportFile factory(String type) {
        if (STANDARD.equals(type)) {
            return new ExportStandardHtmlFile();
        } else if (FINANCIAL.equals(type)) {
            return new ExportFinancialHtmlFile();
        } else {
            throw new RuntimeException("没有找到合适的Html类型");
        }
    }
}

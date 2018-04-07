package com.example.javaee.designPattern.factory.polymorphicfactory;

/**
 * @author tgl
 * @data create in 23:42 2018/4/6
 */
public class ExportPDFFactory implements ExportFactory {
    private static final String STANDARD = "STANDARD";
    private static final String FINANCIAL = "FINANCIAL";
    @Override
    public ExportFile factory(String type) {
        if (STANDARD.equals(type)) {
            return new ExportStandardPDFFile();
        } else if (FINANCIAL.equals(type)) {
            return new ExportFinancialPDFFile();
        } else {
            throw new RuntimeException("没有找到合适的PDF类型");
        }
    }
}

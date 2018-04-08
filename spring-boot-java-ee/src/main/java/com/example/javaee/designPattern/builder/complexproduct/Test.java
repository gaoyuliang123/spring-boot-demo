package com.example.javaee.designPattern.builder.complexproduct;

/**
 * 使用建造模式构建复杂对象
 * @author tgl
 * @data create in 17:14 2018/4/7
 */
public class Test {
    public static void main(String[] args){
        // 创建构建器对象
        InsuranceConstract.ConcreteBuilder builder =
                new InsuranceConstract.ConcreteBuilder("1314", 123L, 456L);
        // 设置需要的数据，然后构建保险合同对象
        InsuranceConstract constract = builder.setPersonName("zhangsan").setOtherData("合同内容-----").builder();
        constract.someOperation();
        System.out.println("合同======>" + constract);
    }
}

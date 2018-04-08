package com.example.javaee.designPattern.builder.complexproduct;

import com.example.javaee.designPattern.builder.ConcreteBuilder;

import java.util.Date;

/**
 * 保险合同
 *
 * 在本例中将具体建造者合并到了产品对象中，并将产品对象的构造函数私有化，防止客户端不使用构建器来构建产品对象，而是直接去使用new来构建产品对象所导致的问题。
 * 另外，这个构建器的功能就是为了创建被构建的对象，完全可以不用单独一个类。
 * @author tgl
 * @data create in 16:58 2018/4/7
 */
public class InsuranceConstract {
    //保险合同编号
    private String contractId;
    /**
     * 被保险人员的名称，同一份保险合同，要么跟人员签订，要么跟公司签订
     * 也就是说，“被保险人员”和“被保险公司”这两个属性，不可能同时有值
     */
    private String personName;
    //被保险公司的名称
    private String companyName;
    //保险开始生效日期
    private long beginDate;
    /**
     * 保险失效日期，一定会大于保险开始生效日期
     */
    private long endDate;
    //其他数据
    private String otherData;

    /**
     * 保险合同的一些操作
     */
    public void someOperation(){
        System.out.println("当前正在操作的保险合同编号为【" + this.contractId + "】");
    }

    private InsuranceConstract(ConcreteBuilder builder) {
        this.contractId = builder.contractId;
        this.personName = builder.personName;
        this.companyName = builder.companyName;
        this.beginDate = builder.beginDate;
        this.endDate = builder.endDate;
        this.otherData = builder.otherData;
    }

    public static class ConcreteBuilder {
        private String contractId;
        private String personName;
        private String companyName;
        private long beginDate;
        private long endDate;
        private String otherData;

        public ConcreteBuilder(String contractId, long beginDate, long endDate) {
            this.contractId = contractId;
            this.beginDate = beginDate;
            this.endDate = endDate;
        }

        public ConcreteBuilder setPersonName(String PersonName) {
            this.personName = PersonName;
            return this;
        }

        public ConcreteBuilder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public ConcreteBuilder setOtherData(String otherData) {
            this.otherData = otherData;
            return this;
        }

        public InsuranceConstract builder() {
            if(contractId == null || contractId.trim().length()==0){
                throw new IllegalArgumentException("合同编号不能为空");
            }
            boolean signPerson = (personName != null && personName.trim().length() > 0);
            boolean signCompany = (companyName != null && companyName.trim().length() > 0);
            if(signPerson && signCompany){
                throw new IllegalArgumentException("一份保险合同不能同时与个人和公司签订");
            }
            if(signPerson == false && signCompany == false){
                throw new IllegalArgumentException("一份保险合同不能没有签订对象");
            }
            if(beginDate <= 0 ){
                throw new IllegalArgumentException("一份保险合同必须有开始生效的日期");
            }
            if(endDate <=0){
                throw new IllegalArgumentException("一份保险合同必须有失效的日期");
            }
            if(endDate < beginDate){
                throw new IllegalArgumentException("一份保险合同的失效日期必须大于生效日期");
            }
            return new InsuranceConstract(this);
        }

    }

    @Override
    public String toString() {
        return "InsuranceConstract{" +
                "contractId='" + contractId + '\'' +
                ", personName='" + personName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", otherData='" + otherData + '\'' +
                '}';
    }
}

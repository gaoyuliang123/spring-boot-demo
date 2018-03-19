package com.example.springbootdrools.drools5;

import com.example.springbootdrools.model.Product;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.util.Collection;


public class Drools5 {

    public static void main(String[] args){
        Drools5 drools5 = new Drools5();
        drools5.runWiDrools5();
    }

    private void runWiDrools5() {
        /**
         * KnowledgeBuilder
         * 在业务代码中收集已编写的规则，并对规则文件进行编译，生成编译好的KnowledgePackage集合，提供给其他API使用。
         * 通过其提供的hasErrors()方法获得编译过程中是否有错，getErrors()方法打印错误信息。
         * 支持.drl文件、.dslr文件和xls文件等。
         */
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("rules/rules5.drl", this.getClass()), ResourceType.DRL);
        if (kbuilder.hasErrors()) {
            System.out.println(kbuilder.getErrors().toString());
        }
        // KnowledgePackage存放编译之后规则的对象
        Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
        /**
         * KnowledgeBase
         * 收集应用当中知识（knowledge）定义的知识库对象（KnowledgePackage），
         * 在一个 KnowledgeBase 当中可以包含普通的规则（rule）、 规则流(rule flow)、
         * 函数定义(function)、用户自定义对象（type model）等，
         * 并创建session对象（StatefulKnowledgeSession和 StatelessKnowledgeSession）
         */
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(pkgs);
        /**
         * StatefulKnowledgeSession
         * 接收外部插入的数据fact对象（POJO），将编译好的规则包和业务数据通过fireAllRules()方法触发所有的规则执行。
         * 使用完成需调用dispose()方法以释放相关内存资源。
         *
         * StatelessKnowledgeSession
         * 对StatefulKnowledgeSession的封装实现，与其对比不需要调用dispose()方法释放内存，只能插入一次fact对象。
         */
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        Product p = new Product();
        p.setType(Product.GOLD);
        ksession.insert(p);
        ksession.fireAllRules();
        ksession.dispose();
        System.out.println("The discount for the product " + p.getType()
                + " is " + p.getDiscount() + "%");
    }
}

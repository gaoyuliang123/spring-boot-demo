package com.example.javaee.designPattern.factory.staticfactorymethod;

public class DomainLogin implements Login {
    @Override
    public boolean verify(String name, String password) {
        System.out.println("------域认证------");
        // 业务逻辑
        return true;
    }
}

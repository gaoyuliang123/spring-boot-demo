package com.example.javaee.designPattern.factory.staticfactorymethod;

public class PasswordLogin implements Login {
    @Override
    public boolean verify(String name, String password) {
        System.out.println("------口令认证------");
        // 业务逻辑
        return true;
    }
}

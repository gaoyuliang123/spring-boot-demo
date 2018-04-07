package com.example.javaee.designPattern.factory.staticfactorymethod;

public class Test {

    public static void main(String[] args){
        Login login = LoginManager.factory("PASSWORD");
        login.verify("zhangsan", "123456");
    }
}

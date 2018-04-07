package com.example.javaee.designPattern.factory.staticfactorymethod;

/**
 * 工厂类
 * LoginManager根据调用者不同的要求，创建出不同的登录对象并返回。
 *
 * @author tgl
 * @data create in 22:38 2018/4/6
 */
public class LoginManager {
    private static final String DOMAIN = "DOMAIN";
    private static final String PASSWORD = "PASSWORD";
    public static Login factory(String type) {
        Login login = null;
        if (DOMAIN.equals(type)) {
            login = new DomainLogin();
        } else if (PASSWORD.equals(type)) {
            login = new PasswordLogin();
        } else {
            throw new RuntimeException("没有找到登录类型");
        }
         return login;
    }
}

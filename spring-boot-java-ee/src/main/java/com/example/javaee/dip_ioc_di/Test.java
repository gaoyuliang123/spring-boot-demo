package com.example.javaee.dip_ioc_di;

public class Test {
    public static void main(String[] args){
        Person p = new Person();
        p.go();

        // IoC
        // Person不再亲自创建Driveable对象，它将依赖的实例化的权力交接给了Test。
        // 而Test 在IoC中又指代了IoC容器这个概念。
        Driveable driveable = new Car();
        Person Person = new Person(driveable);
        Person.go();
    }
}

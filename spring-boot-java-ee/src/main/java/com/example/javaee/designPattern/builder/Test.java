package com.example.javaee.designPattern.builder;

/**
 * Client
 * @author tgl
 * @data create in 16:36 2018/4/7
 */
public class Test {
    public static void main(String[] args){
        Builder builder = new ConcreteBuilder();
        Director director = new Director();
        Person person = director.constructPerson(builder);
        System.out.println(person);
    }
}

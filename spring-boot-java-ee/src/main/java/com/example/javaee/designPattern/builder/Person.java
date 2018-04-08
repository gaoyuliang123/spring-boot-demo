package com.example.javaee.designPattern.builder;

/**
 * Product（产品）
 * 要创建的复杂对象
 * @author tgl
 * @data create in 16:17 2018/4/7
 */
public class Person {

    private String heat;
    private String body;
    private String foot;

    public String getHeat() {
        return heat;
    }

    public void setHeat(String heat) {
        this.heat = heat;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFoot() {
        return foot;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }

    @Override
    public String toString() {
        return "Person{" +
                "heat='" + heat + '\'' +
                ", body='" + body + '\'' +
                ", foot='" + foot + '\'' +
                '}';
    }
}

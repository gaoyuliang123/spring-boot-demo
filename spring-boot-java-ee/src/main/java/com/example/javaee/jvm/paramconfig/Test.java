package com.example.javaee.jvm.paramconfig;

import java.util.Vector;

public class Test {
    public static void main(String[] args){
//        byte[] b = new byte[1*1024*1024];
//        byte[] b = new byte[4*1024*1024];
//        System.gc();
//        System.out.println("Xmx=" + Runtime.getRuntime().maxMemory());
//        System.out.println("free mem=" + Runtime.getRuntime().freeMemory());
//        System.out.println("total mem=" + Runtime.getRuntime().totalMemory());

//        byte[] b = null;
//        for (int i = 0; i < 10; i++) {
//            b = new byte[1*1024*1024];
//        }

        Vector v = new Vector();
        for (int i = 0; i < 25; i++) {
            byte[] b = new byte[1*1024*1024];
            v.add(b);
        }
    }
}

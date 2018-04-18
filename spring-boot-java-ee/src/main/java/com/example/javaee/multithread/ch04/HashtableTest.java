package com.example.javaee.multithread.ch04;

import java.util.Hashtable;

/**
 * @author tgl
 * @data create in 9:19 2018/4/15
 */
public class HashtableTest {
    public static void main(String[] args){
        Hashtable<String, Integer> ha = new Hashtable<>();
        ha.put("one", 1);
        ha.put("two", 2);
        ha.put("three", 3);
        System.out.println("get value two======>" + ha.get("two"));
        System.out.println("size=====>" + ha.size());
        System.out.println("containsKey=====>" + ha.containsKey("three"));
        System.out.println("remove return two=====>" + ha.remove("two"));
        // other method
    }
}

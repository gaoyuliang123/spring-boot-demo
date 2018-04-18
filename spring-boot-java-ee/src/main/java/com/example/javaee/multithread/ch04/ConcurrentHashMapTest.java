package com.example.javaee.multithread.ch04;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tgl
 * @data create in 9:48 2018/4/15
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args){
        ConcurrentHashMap<String, Integer> cm = new ConcurrentHashMap<>();
        cm.put("one", 1);
        cm.put("two", 2);
        cm.put("three", 3);
        //  遍历
        Set<Map.Entry<String, Integer>> entrySet = cm.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.println("key-value======>" + entry.getKey() + "=" + entry.getValue());
        }
        System.out.println("get value two======>" + cm.get("two"));
        System.out.println("size=====>" + cm.size());
        System.out.println("containsKey=====>" + cm.containsKey("three"));
        System.out.println("remove return two=====>" + cm.remove("two"));
        // 还有其他方法 参考其API
    }
}

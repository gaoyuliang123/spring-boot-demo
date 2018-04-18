package com.example.javaee.multithread.ch04;

import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author tgl
 * @data create in 10:18 2018/4/15
 */
public class CopyOnWriteArrayListTest {
    public static void main(String[] args){
        CopyOnWriteArrayList<String> cwl = new CopyOnWriteArrayList();
        cwl.add("zhangsan");
        cwl.add("lisi");
        cwl.add("wangwu");
        cwl.add("zhaoliu");
        cwl.add(0,  "xiaoer");
        // 遍历
        ListIterator<String> listIterator = cwl.listIterator();
        while (listIterator.hasNext()) {
            System.out.println("list name======>" + listIterator.next());
        }
        Object[] names = cwl.toArray();
        for (Object name : names) {
            System.out.println("array name======>" + (String)name);
        }
        System.out.println("is contains======>" + cwl.contains("lisi"));
        System.out.println("get by index======>" + cwl.get(3));
        System.out.println("remove by index======>" + cwl.remove(4));
        System.out.println("is empty======>" + cwl.isEmpty());
        System.out.println("size======>" + cwl.size());
        System.out.println("indexOf======>" + cwl.indexOf("lisi"));
        // 还有其他方法 参考其API
    }
}

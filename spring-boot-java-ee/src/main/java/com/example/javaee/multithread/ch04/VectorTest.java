package com.example.javaee.multithread.ch04;

import java.util.ListIterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author tgl
 * @data create in 10:18 2018/4/15
 */
public class VectorTest {
    public static void main(String[] args){
        Vector<String> v = new Vector();
        v.addElement("one");
        v.addElement("two");
        v.addElement("three");
        v.addElement("four");
        // 遍历
        ListIterator<String> listIterator = v.listIterator();
        while (listIterator.hasNext()) {
            System.out.println("list num======>" + listIterator.next());
        }
        System.out.println("is contains======>" + v.contains("two"));
        System.out.println("get by index======>" + v.get(3));
        System.out.println("remove by index======>" + v.remove(3));
        System.out.println("is empty======>" + v.isEmpty());
        System.out.println("size======>" + v.size());
        System.out.println("indexOf======>" + v.indexOf("two"));
        // 还有其他方法 参考其API
    }
}

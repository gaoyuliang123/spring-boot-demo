package com.example.javaee.multithread.ch05.priorityblockingqueue.Humen;

import com.example.javaee.multithread.ch05.priorityblockingqueue.Humen.Human;

import java.util.Comparator;

/**
 * 钱比较类
 * @author tgl
 * @data create in 23:15 2018/4/17
 */
public class HumanComparator implements Comparator<Human> {
    @Override
    public int compare(Human o1, Human o2) {
        return o2.getMoney() - o1.getMoney();
    }
}

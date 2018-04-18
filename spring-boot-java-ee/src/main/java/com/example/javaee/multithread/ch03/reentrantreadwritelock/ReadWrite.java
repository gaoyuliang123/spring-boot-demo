package com.example.javaee.multithread.ch03.reentrantreadwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author tgl
 * @data create in 12:12 2018/4/14
 */
public class ReadWrite {
    // 创建一个ReentrantReadWriteLock对象
    private final ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    // 抽取读锁和写锁
    private Lock readLock = rw.readLock();
    private Lock writeLock = rw.writeLock();
    private Map<String, String> database = new HashMap<>();
    public String getAndPutValue(String key) {
        String value = "";
        try {
            // 对所有访问者加锁
            readLock.lock();
            value = database.get(key);
            if (value == null) {
                readLock.unlock();
                // 对所有修改者加锁
                writeLock.lock();
                if (value == null) {
                    value = "lock";
                    database.put(key, value);
                }
                writeLock.unlock();
                readLock.lock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return value;
    }
    public Map<String, String> getDatabase() {
        return database;
    }

    public void setDatabase(Map<String, String> database) {
        this.database = database;
    }
}

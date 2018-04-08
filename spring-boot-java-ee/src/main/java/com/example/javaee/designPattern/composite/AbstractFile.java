package com.example.javaee.designPattern.composite;

/**
 * @author tgl
 * @data create in 21:36 2018/4/8
 */
public abstract class AbstractFile {

    void add(AbstractFile abstractFile) {
        throw new UnsupportedOperationException("对不起，不支持该方法");
    };

    void remove(AbstractFile abstractFile) {
        throw new UnsupportedOperationException("对不起，不支持该方法");
    };

    AbstractFile getChild(int index) {
        throw new UnsupportedOperationException("对不起，不支持该方法");
    }

    abstract void killVirus();
}

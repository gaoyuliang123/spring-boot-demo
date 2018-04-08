package com.example.javaee.designPattern.composite;

import com.example.javaee.designPattern.composite.AbstractFile;

/**
 * @author tgl
 * @data create in 21:42 2018/4/8
 */
public class TextFile extends AbstractFile {
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    @Override
    void killVirus() {
        // 模拟杀毒
        System.out.println("-------对Text文件======>" + name + "进行杀毒------");
    }
}

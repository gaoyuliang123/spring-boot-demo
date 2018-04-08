package com.example.javaee.designPattern.composite;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * @author tgl
 * @data create in 21:42 2018/4/8
 */
public class Folder extends AbstractFile {
    private String name;
    private ArrayList<AbstractFile> files = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    @Override
    void add(AbstractFile abstractFile) {
        files.add(abstractFile);
    }

    @Override
    void remove(AbstractFile abstractFile) {
        ListIterator<AbstractFile> iterables = files.listIterator();
        while (iterables.hasNext()) {
           if (iterables.next() == abstractFile) {
               iterables.remove();
           };
        }
    }

    @Override
    AbstractFile getChild(int index) {
        return files.get(index);
    }

    @Override
    void killVirus() {
        // 模拟杀毒
        System.out.println("-------对文件夹======>" + name + "进行杀毒------");
        ListIterator<AbstractFile> iterables = files.listIterator();
        while (iterables.hasNext()) {
            iterables.next().killVirus();
        }
    }
}

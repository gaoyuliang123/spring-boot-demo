package com.example.javaee.designPattern.composite;

/**
 * @author tgl
 * @data create in 21:42 2018/4/8
 */
public class ImageFile extends AbstractFile {
    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    @Override
    void killVirus() {
        // 模拟杀毒
        System.out.println("-------对图像======>" + name + "进行杀毒------");
    }
}

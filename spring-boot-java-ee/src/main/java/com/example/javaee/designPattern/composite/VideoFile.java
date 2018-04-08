package com.example.javaee.designPattern.composite;

/**
 * @author tgl
 * @data create in 21:42 2018/4/8
 */
public class VideoFile extends AbstractFile {
    private String name;

    public VideoFile(String name) {
        this.name = name;
    }

    @Override
    void killVirus() {
        // 模拟杀毒
        System.out.println("-------对视频======>" + name + "进行杀毒------");
    }
}

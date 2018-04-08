package com.example.javaee.designPattern.composite;

/**
 * @author tgl
 * @data create in 22:05 2018/4/8
 */
public class Test {
    public static void main(String[] args){
        AbstractFile folder = new Folder("Sunny文件夹");
        AbstractFile folder1 = new Folder("图像文件夹");
        AbstractFile folder2 = new Folder("Text文件夹");
        AbstractFile folder3 = new Folder("视频文件夹");
        AbstractFile folder4 = new ImageFile("苍老师.jpg");
        AbstractFile folder5 = new ImageFile("马蓉.png");
        AbstractFile folder6 = new TextFile("马蓉出轨记录.txt");
        AbstractFile folder7 = new VideoFile("岛国片.rmvb");

        folder.add(folder1);
        folder.add(folder2);
        folder.add(folder3);
        folder1.add(folder4);
        folder1.add(folder5);
        folder2.add(folder6);
        folder3.add(folder7);
        folder.killVirus();
        folder6.add(folder1);
    }

}

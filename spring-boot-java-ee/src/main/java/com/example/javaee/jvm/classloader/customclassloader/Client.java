package com.example.javaee.jvm.classloader.customclassloader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Client {
    public static void main(String[] args){
        String byteCodePathSource = "D:\\IdeaProjects\\spring-boot-demo\\classes\\production\\spring-boot-java-ee\\com\\example\\javaee\\jvm\\classloader\\";
//        String byteCodePathTarget = "D:/IdeaProjects/spring-boot-demo/classes/production/spring-boot-java-ee/com/example/javaee/jvm/classloader/";
        // 文件加密
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        byte[] key = "01234567899876543210abcd".getBytes();
        try {
            in = new BufferedInputStream(new FileInputStream(byteCodePathSource + "Test.class"));
            byte[] source = new byte[in.available()];
            in.read(source);
            in.close();
            // 将字节码Test.class加密后写到TestEncrypt.class
            out = new BufferedOutputStream(
                    new FileOutputStream(byteCodePathSource + "TestEncrypt.class"));
            out.write(ThreeDES.encrypt(key, source));
            out.close();
            MyClassLoader classLoader = new MyClassLoader(byteCodePathSource, key);
//            String className = classLoader.loadClass("TestEncrypt").getName();
            String className = classLoader.loadClass("TestEncrypt").getClassLoader().getClass().getName();
            System.out.println(className);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null ) {
                    in.close();
                }
                if (out != null ) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.example.javaee.jvm.classloader.customclassloader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
    /**
     * 源字节码路径
     */
    private String byteCodePath;

    private byte[] key;

    public MyClassLoader(String byteCodePath, byte[] key) {
        this.byteCodePath = byteCodePath;
        this.key = key;
    }

    /**
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] value = null;
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(byteCodePath + name + ".class"));
            System.out.println("length======>" + in.available());
            value = new byte[in.available()];
            in.read(value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 将加密后的字节码解密
        value = ThreeDES.decrypt(key, value);
        System.out.println("解密后======>" + new String(value));
        // 将byte数组转化为一个类的Class对象实例
        return defineClass(value, 0, value.length);
    }
}

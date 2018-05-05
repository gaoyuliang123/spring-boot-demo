package com.example.javaee.jvm.classloader.customclassloader;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ThreeDES {
    private static final String ALGORITHM = "DESede";//定义加密算法
    /**
     *
     * @param key  192位的加密Key
     * @param src  明文数据
     * @return
     */
    public static byte[] encrypt(byte[] key, byte[] src){
        byte[] value = null;
        SecretKey deskey = new SecretKeySpec(key, ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            value = cipher.doFinal(src);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 解密
     * @param key  192位的加密Key
     * @param src  待解密数据
     * @return
     */
    public static byte[] decrypt(byte[] key, byte[] src){
        byte[] value = null;
        SecretKey deskey = new SecretKeySpec(key, ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, deskey);
            value = cipher.doFinal(src);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return value;
    }

    //测试
    public static void main(String[] args) {
        byte[] key = "01234567899876543210abcd".getBytes();
        byte[] encoded = encrypt(key, "554278".getBytes());
        System.out.println("加密后"+encoded);
        System.out.println("解密后"+new String(decrypt(key, encoded)));
    }
}

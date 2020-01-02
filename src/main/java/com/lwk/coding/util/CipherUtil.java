package com.lwk.coding.util;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @author kai
 * @date 2020-01-02 23:23
 */
@Slf4j
public class CipherUtil {

    private final static String DES = "DES";

    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) {
        try {
            byte[] bt = encrypt(data.getBytes(), key.getBytes());
            String res = new BASE64Encoder().encode(bt);
        } catch (Exception e) {
            log.error("加密异常，请检查确认！", e);
        }
        return null;
    }

    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String key) {
        if (data == null) {
            log.error("输入的解密密码为空，请检查确认！");
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] buf = decoder.decodeBuffer(data);
            byte[] res = decrypt(buf,key.getBytes());
            return new String(res);
        } catch (Exception e) {
            log.error("解密异常，请检查确认！", e);
        }
        return null;
    }

    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);

        return cipher.doFinal(data);
    }


    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);

        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        String data = "123456";
        String key = "NICAIYICAI";
        String res = encrypt(data, key);
        System.out.println("源数据:" + data);
        System.out.println("加密后:" + res);//2duoHZnKNpw=
        System.out.println("解密后:" + decrypt(res, key));

    }

}

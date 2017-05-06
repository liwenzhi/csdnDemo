package com.example.encryption;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

import java.io.IOException;

/**
 * 加密解密程序示例
 * <p/>
 * <p/>
 * /**
 * 编码工具类
 * 1.将byte[]转为各种进制的字符串
 * 2.base 64 encode
 * 3.base 64 decode
 * 4.获取byte[]的md5值
 * 5.获取字符串md5值
 * 6.结合base64实现md5加密
 * 7.AES加密
 * 8.AES加密为base 64 code
 * 9.AES解密
 * 10.将base 64 code AES解密
 *
 * @author uikoo9
 * @version 0.0.7.20140601
 */
public class EncryptionDemo {


    public static void main(String[] args) {
        final int REPEATTIMES = 5; //加密解密的次数
        String s = "我的字符串seasonszx";
        System.out.println("原字符串：" + s);
        String encryptString = encryptBASE64(s, REPEATTIMES);
        System.out.println("加密后：" + encryptString);
        System.out.println("解密后：" + decryptBASE64(encryptString, REPEATTIMES));
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptBASE64(String key, int repeatTimes) {
        byte[] bt;
        try {
            while (repeatTimes > 0) {
                bt = (new BASE64Decoder()).decodeBuffer(key);     //解密一次
                key = new String(bt);//如果出现乱码可以改成： String(bt, "utf-8")或 gbk
                repeatTimes--;
            }
            return key;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(String key, int repeatTimes) {
        //加密repeatTimes次，个人风格
        while (repeatTimes > 0) {
            byte[] bt = key.getBytes();
            key = (new BASE64Encoder()).encodeBuffer(bt);  //加密一次返回字符串
            repeatTimes--;
        }

        return key;
    }
}
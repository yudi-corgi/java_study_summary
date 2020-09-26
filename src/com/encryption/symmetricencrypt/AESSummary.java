package com.encryption.symmetricencrypt;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

/**
 * AES 加密算法
 * @author YUDI
 * @date 2020/9/26 16:28
 */
public class AESSummary {

    private static String plainText = "Emptiness,fatigue,helplessness,loneliness";

    public static void main(String[] args) throws Exception {
        aes();
    }

    public static void aes() throws Exception{
        // 加入 Bouncy Castle 扩展
        // Security.addProvider(new BouncyCastleProvider());

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        // 初始化 Key 长度，SecureRandom() 会根据算法生成相应长度
        keyGenerator.init(new SecureRandom());
        // 生成 Key
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] bytesKey = secretKey.getEncoded();

        // 转换 KEY，DES 中的 KEY 转换也可简化成这一步
        Key key = new SecretKeySpec(bytesKey,"AES");

        // 加密
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        // 初始化为加密模式，并传入密钥
        cipher.init(Cipher.ENCRYPT_MODE,key);
        // 执行加密
        byte[] encryptBytes = cipher.doFinal(plainText.getBytes());
        System.out.println("AES encrypt："+ Hex.encodeHexString(encryptBytes));

        // 初始化为解密模式，并传入密钥
        cipher.init(Cipher.DECRYPT_MODE,key);
        // 执行解密
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        System.out.println("AES decrypt："+ new String(decryptBytes));
    }

}

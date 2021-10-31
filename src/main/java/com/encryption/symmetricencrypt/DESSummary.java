package com.encryption.symmetricencrypt;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * DES 对称加密
 * @author YUDI
 * @date 2020/9/26 15:02
 */
public class DESSummary {

    private static String plainText = "The life of one person";

    public static void main(String[] args) throws Exception {
        desOr3Des();
    }

    public static void desOr3Des() throws Exception {
        // 使用 Bouncy Castle
        // Security.addProvider(new BouncyCastleProvider());

        // 指定算法初始化 KeyGenerator，若用 Bouncy Castle，则第二个参数传递 BC
        // KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");
        // System.out.println("BC provider："+keyGenerator.getProvider());
        // 若为 3DES 将参数改为 DESede
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        // 初始化 Key 长度，SecureRandom() 会根据算法生成相应长度
        keyGenerator.init(new SecureRandom());
        // 生成 Key
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] bytesKey = secretKey.getEncoded();

        // KEY 转换,创建密钥工厂，将 DESKeySpec 转换成 SecretKey
        // 这一步 SecretKey 转换时因为 key 的生成可以自定义，不一定要 KeyGenerator 生成
        DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
        // 若用 3DES 则将参数改为 DESede 实例化密钥工厂，并且 KeySpec 为以下方式实例化
        // DESedeKeySpec desKeySpec3 = new DESedeKeySpec(bytesKey);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        Key convertSecretKey = factory.generateSecret(desKeySpec);

        // 指定加密算法，算法名称/工作模式/填充模式，若用 3DES 则将算法名称改为 DESede
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        // 初始化为加密模式，并传入密钥
        cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
        // 执行加密
        byte[] encryptBytes = cipher.doFinal(plainText.getBytes());
        System.out.println("DES encrypt："+ Hex.encodeHexString(encryptBytes));

        // 初始化为解密模式，并传入密钥
        cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
        // 执行解密
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        System.out.println("DES decrypt："+ new String(decryptBytes));
    }

}

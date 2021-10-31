package com.encryption.messageDigestAlgorithm;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * MAC(HMAC)：含有密钥的散列函数算法
 * @author YUDI
 * @date 2020/9/26 13:56
 */
public class MACSummary {

    private static String plaintext = "Virtue is no more than a luxury";

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException {
        jdkHmac();
    }

    // JDK 实现
    public static void jdkHmac() throws NoSuchAlgorithmException, InvalidKeyException {
        // 1、模拟发送方
        // 1.1、指定算法初始化 KeyGenerator
        // KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA224");
        // 1.2、产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 1.3、获取密钥字节数组
        byte[] key = secretKey.getEncoded();

        // 2、模拟接收方(JDK)
        // 2.1、接收到 key 后还原密钥
        // SecretKey restoreSecretKey = new SecretKeySpec(key,"HmacMD5");
        SecretKey restoreSecretKey = new SecretKeySpec(key,"HmacSHA224");
        // 2.2、实例化 Mac
        Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
        // 2.3、为 Mac 初始化密钥
        mac.init(restoreSecretKey);
        // 2.5、摘要计算，doFinal 内部会更新一次摘要
        byte[] hmacMD5Bytes = mac.doFinal(plaintext.getBytes());
        System.out.println("JDK HmacMD5："+ Hex.encodeHexString(hmacMD5Bytes));

        // 3、模拟接收方（Bouncy-castle）
        bcHmac(key);
    }

    // bouncy-castle 实现
    public static void bcHmac(byte[] key){
        // HMac hmacMD = new HMac(new MD5Digest());
        HMac hmacSHA = new HMac(new SHA224Digest());
        hmacSHA.init(new KeyParameter(key));
        hmacSHA.update(plaintext.getBytes(),0,plaintext.length());
        byte[] hmacMD5Bytes = new byte[hmacSHA.getMacSize()];
        hmacSHA.doFinal(hmacMD5Bytes,0);
        System.out.println("BC HmacMD5：" + org.bouncycastle.util.encoders.Hex.toHexString(hmacMD5Bytes));
    }
}

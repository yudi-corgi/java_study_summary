package com.encryption.messageDigestAlgorithm;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * SHA 安全散列算法
 * @author YUDI
 * @date 2020/9/26 12:35
 */
public class SHASummary {

    private static String plaintext = "Justice,that's a pretty word";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        jdkSHA();
        bcSHA();
        ccSHA();
    }

    // JDK 实现
    public static void jdkSHA() throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest sha3 = MessageDigest.getInstance("SHA3-256");
        System.out.println("SHA3 实现者："+sha3.getProvider());
        // 创建 SHA 消息摘要
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        // 更新摘要信息
        sha256.update(plaintext.getBytes());
        // 摘要计算
        byte[] shaBytes = sha256.digest();
        System.out.println("jdk SHA-256："+ Hex.encodeHexString(shaBytes));
    }

    // bouncy-castle 实现
    public static void bcSHA(){
        Digest sha3 = new SHA3Digest(224);
        System.out.println("BC 实现 sha3，传递数值即摘要长度，默认256："+sha3.getDigestSize());
        // 创建 SHA 消息摘要，其它如 SHA1/SHA512 创建也是如此.
        Digest sha256 = new SHA256Digest();
        // 更新摘要
        sha256.update(plaintext.getBytes(),0,plaintext.length());
        byte[] sha256Bytes = new byte[sha256.getDigestSize()];
        sha256.doFinal(sha256Bytes,0);
        System.out.println("bc SHA-256："+ org.bouncycastle.util.encoders.Hex.toHexString(sha256Bytes));
    }

    // commons-codec 实现
    public static void ccSHA(){
        // 内部会自动调用 update() 更新一次摘要信息
        System.out.println("cc SHA-256："+DigestUtils.sha256Hex(plaintext));
    }

}

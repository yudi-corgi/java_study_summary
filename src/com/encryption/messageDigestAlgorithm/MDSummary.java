package com.encryption.messageDigestAlgorithm;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * MD 消息摘要
 * @author YUDI
 * @date 2020/9/25 0:59
 */
public class MDSummary {

    private static String plaintext = "sleep is for the guiltless";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        jdkMD();
        bcMD();
        ccMD();
    }

    // JDK 实现，字节数组转十六进制字符使用的是 commons-codec 的方法
    public static void jdkMD() throws NoSuchAlgorithmException {
        MessageDigest md2 = MessageDigest.getInstance("MD2");
        // JDK 借助 bouncy-castle 使用 MD4，为 JCE 加入扩展包
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest md4 = MessageDigest.getInstance("MD4");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] md2Bytes = md2.digest(plaintext.getBytes());
        byte[] md4Bytes = md4.digest(plaintext.getBytes());
        byte[] md5Bytes = md5.digest(plaintext.getBytes());
        System.out.println("JDK MD2：" + Hex.encodeHexString(md2Bytes));
        System.out.println("JDK MD4：" + Hex.encodeHexString(md4Bytes));
        System.out.println("JDK MD5：" + Hex.encodeHexString(md5Bytes));
    }

    // bouncy-castle 实现，需要 bouncy-castle 第三方 jar
    public static void bcMD(){
        Digest digest2 = new MD2Digest();
        Digest digest4 = new MD4Digest();
        Digest digest5 = new MD5Digest();
        digest2.update(plaintext.getBytes(),0,plaintext.getBytes().length);
        digest4.update(plaintext.getBytes(),0,plaintext.getBytes().length);
        digest5.update(plaintext.getBytes(),0,plaintext.getBytes().length);
        byte[] md2Bytes = new byte[digest2.getDigestSize()];
        byte[] md4Bytes = new byte[digest4.getDigestSize()];
        byte[] md5Bytes = new byte[digest5.getDigestSize()];
        // 将摘要信息写入到字节数组
        digest2.doFinal(md2Bytes,0);
        digest4.doFinal(md4Bytes,0);
        digest5.doFinal(md5Bytes,0);
        System.out.println("bouncy-castle MD2：" + org.bouncycastle.util.encoders.Hex.toHexString(md2Bytes));
        System.out.println("bouncy-castle MD4：" + org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));
        System.out.println("bouncy-castle MD5：" + org.bouncycastle.util.encoders.Hex.toHexString(md5Bytes));
    }

    // commons-codec 实现，该扩展其实是对 JDK MD 的封装，需要 commons-codec 第三方 jar
    public static void ccMD(){
        System.out.println("commons-codec MD2：" + DigestUtils.md2Hex(plaintext.getBytes()));
        System.out.println("commons-codec MD5：" + DigestUtils.md5Hex(plaintext.getBytes()));
    }

}

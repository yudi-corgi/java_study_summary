package com.encryption;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Base64 加密算法
 * @author YUDI
 * @date 2020/9/24 0:06
 */
public class Base64Summary {

    private static String plaintext = "one blade,one purpose";

    public static void main(String[] args) throws IOException {
        jdkBase64();
        commonsCodecBase64();
        bouncyCastleBase64();
    }

    public static void jdkBase64() throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        String encryptedStr = encoder.encode(plaintext.getBytes());
        System.out.println("JDK 原生 Base64 加密：" + encryptedStr);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] sourceText = decoder.decodeBuffer(encryptedStr);
        System.out.println("JDK 原生 Base64 解密：" + new String(sourceText, StandardCharsets.UTF_8));
    }

    //需要导入 commons-codec jar包（Apache 第三方扩展）
    public static void commonsCodecBase64(){
        byte[] encryptBytes = Base64.encodeBase64(plaintext.getBytes());
        System.out.println("commons-codec Base64 加密：" + new String(encryptBytes, StandardCharsets.UTF_8));
        byte[] sourceBytes = Base64.decodeBase64(encryptBytes);
        System.out.println("commons-codec Base64 解密：" + new String(sourceBytes, StandardCharsets.UTF_8));
    }

    //需要导入 bouncy-castle jar包（第三方扩展）
    public static void bouncyCastleBase64(){
        byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(plaintext.getBytes());
        System.out.println("bouncy-castle Base64 加密：" + new String(encodeBytes, StandardCharsets.UTF_8));
        byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
        System.out.println("bouncy-castle Base64 解密：" + new String(decodeBytes, StandardCharsets.UTF_8));
    }


}
